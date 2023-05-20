package by.teachmeskills.hw_12052023.utils;

import by.teachmeskills.hw_12052023.exceptions.BankAccountNotFoundException;
import by.teachmeskills.hw_12052023.exceptions.MerchantNotFoundException;
import by.teachmeskills.hw_12052023.model.AccountStatus;
import by.teachmeskills.hw_12052023.model.BankAccount;
import by.teachmeskills.hw_12052023.model.Merchant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {

    private static final String INSERT_MERCHANT_QUERY = "INSERT INTO merchant (id, nameMerchant, createdAt) Values (?, ?, ?)";
    private static final String DELETE_MERCHANT_QUERY = "DELETE FROM merchant WHERE id = ?";
    private static final String GET_MERCHANTS_QUERY = "SELECT * FROM merchant";
    private static final String SELECT_MERCHANT_ID_QUERY = "SELECT * FROM merchant WHERE id = ?";

    private final static String INSERT_BANK_ACCOUNT_QUERY = "INSERT INTO bank_accounts (id, merchantId, status, accountNumber, createdAt) Values (?, ?, ?, ?, ?)";
    private final static String GET_MERCHANT_BANK_ACCOUNTS = "SELECT * FROM bank_accounts WHERE merchantId = ? ORDER BY status ASC, createdAt ASC";
    private static final String DELETE_BANK_ACCOUNT_QUERY = "DELETE FROM bank_accounts WHERE merchantId = ? AND accountNumber = ?";
    private static final String DELETE_BANK_ACCOUNT_All = "DELETE FROM bank_accounts WHERE merchantId = ?";
    private static final String UPDATE_ACCOUNT_NUMBER = "UPDATE bank_accounts SET accountNumber = ? WHERE merchantId = ? AND accountNumber = ?";
    private static final String COUNT_BANK_ACCOUNT = "SELECT * FROM bank_accounts WHERE accountNumber = ? AND merchantId = ?";

    private static final Connection CONNECTION = DbUtils.getConnection();
    private static PreparedStatement preparedStatement;

    public static void createMerchant(Merchant merchant) { //создание мерчанта (1)
        try {
            preparedStatement = CONNECTION.prepareStatement(INSERT_MERCHANT_QUERY);
            preparedStatement.setString(1, merchant.getId());
            preparedStatement.setString(2, merchant.getName());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(merchant.getCreatedAt()));
            preparedStatement.executeUpdate();
            System.out.println("Мерчант добавлен в базу");
        } catch (SQLException e) {
            System.out.println("При добавлении мерчанта в базу произошла ошибка");
        }
    }

    public static List<Merchant> getAllMerchants() { //возвращает всех мерчантов (2)
        try {
            ResultSet resultSet = CONNECTION.createStatement().executeQuery(GET_MERCHANTS_QUERY);
            List<Merchant> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Merchant(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getTimestamp(3).toLocalDateTime()));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Merchant getMerchantById(String id) throws MerchantNotFoundException { // получение информации о мерчанте по id (3)
        try {
            preparedStatement = CONNECTION.prepareStatement(SELECT_MERCHANT_ID_QUERY);
            preparedStatement.setString(1, id);
            ResultSet set = preparedStatement.executeQuery();
            set.next();
            return new Merchant(set.getString(1), set.getString(2),
                    set.getTimestamp(3).toLocalDateTime());
        } catch (SQLException e) {
            throw new MerchantNotFoundException("Мерчант с ID " + id + " отсутствует в базе.\n\n");
        }
    }

    public static void deleteMerchantById(String idDelete) { // удаление мерчанта (4)
        try {
            preparedStatement = CONNECTION.prepareStatement(DELETE_MERCHANT_QUERY);
            preparedStatement.setString(1, idDelete);
            if (preparedStatement.executeUpdate() == 0) {
                System.out.println("Мерчант с ID " + idDelete + " отсутствует в базе\n");
            } else {
                deleteBankAccountAll(idDelete);
                System.out.println("Мерчент с ID " + idDelete + " успешно удален!\n");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("При удалении мерчанта из базы произошла ошибка\n");
        }
    }

    public static void deleteBankAccountAll(String idDelete) { // удаление всех аккаунтов мерчанта (4)
        try {
            preparedStatement = CONNECTION.prepareStatement(DELETE_BANK_ACCOUNT_All);
            preparedStatement.setString(1, idDelete);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addBankAccount(BankAccount bankAccount) { // добавление нового банковского аккаунта мерчанту (5)
        try {
            preparedStatement = CONNECTION.prepareStatement(INSERT_BANK_ACCOUNT_QUERY);
            preparedStatement.setString(1, bankAccount.getId());
            preparedStatement.setString(2, bankAccount.getMerchantId());
            preparedStatement.setString(3, bankAccount.getStatus().toString());
            preparedStatement.setString(4, bankAccount.getAccountNumber());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(bankAccount.getCreatedAt()));
            preparedStatement.executeUpdate();
            System.out.println("Банковский аккаунт добавлен в базу\n");
        } catch (SQLException e) {
            System.out.println("При добавлении банковского аккаунта в базу произошла ошибка\n");
        }
    }

    public static List<BankAccount> getMerchantBankAccounts(String idMerchant) {// получение информации о банковских аккаунтах мерчанта (6)
        List<BankAccount> bankAccounts = new ArrayList<>();
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(GET_MERCHANT_BANK_ACCOUNTS)) {
            preparedStatement.setString(1, idMerchant);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                AccountStatus status = (set.getString("status").equals("ACTIVE")) ?
                        AccountStatus.ACTIVE : AccountStatus.DELETED;
                bankAccounts.add(new BankAccount(set.getString("merchantId"), set.getString("id"),
                        status, set.getString("accountNumber"), set.getTimestamp("createdAt").toLocalDateTime()));
            }
        } catch (SQLException e) {
            System.out.println("Банковский аккаунт не найден");
        }
        return bankAccounts;
    }

    public static void updateBankAccount(String newNumber, String merchant_id, String account_number) throws BankAccountNotFoundException {
        // редактирование банковского аккаунта мерчанта (7)
        try {
            preparedStatement = CONNECTION.prepareStatement(UPDATE_ACCOUNT_NUMBER);
            preparedStatement.setString(1, newNumber);
            preparedStatement.setString(2, merchant_id);
            preparedStatement.setString(3, account_number);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new BankAccountNotFoundException("Такой банковский аккаунт не найден");
        }
    }

    public static void deleteBankAccountById(String merchantId, String accountNumber) { // удаление банковского аккаунта мерчанта (8)
        try {
            preparedStatement = CONNECTION.prepareStatement(DELETE_BANK_ACCOUNT_QUERY);
            preparedStatement.setString(1, merchantId);
            preparedStatement.setString(2, accountNumber);
            int result = preparedStatement.executeUpdate();
            if (result == 0) {
                System.out.println("Банковский аккаунт с id " + accountNumber + " отсутствует в базе\n");
            } else {
                System.out.println("Банковский аккаунт с id - " + accountNumber + " успешно удален у мерчанта " + merchantId + "\n");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("При удалении банковского аккаунта из базы произошла ошибка\n");
        }
    }

    public static int searchDuplicateAccount(BankAccount bankAccount) { //поиск дубликата аккаунта
        try {
            preparedStatement = CONNECTION.prepareStatement(COUNT_BANK_ACCOUNT);
            preparedStatement.setString(1, bankAccount.getAccountNumber());
            preparedStatement.setString(2, bankAccount.getMerchantId());
            ResultSet resultSet = preparedStatement.executeQuery();
            int size = 0;
            while (resultSet.next()) {
                size++;
            }
            return size;
        } catch (SQLException e) {
            return 0;
        }
    }
}
