package by.teachmeskills.hw_12052023.utils;

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

    private static final String INSERT_MERCHANT_QUERY = "INSERT INTO merchant (id, name, createdAt) Values (?, ?, ?)";
    private static final String DELETE_MERCHANT_QUERY = "DELETE FROM merchant WHERE id = ?";
    private static final String GET_MERCHANTS_QUERY = "SELECT * FROM merchant";
    private static final String SELECT_MERCHANT_ID_QUERY = "SELECT * FROM merchant WHERE id = ?";

    private final static String INSERT_BANK_ACCOUNT_QUERY = "INSERT INTO bank_accounts (id, merchantId, status, accountNumber, createdAt) Values (?, ?, ?, ?, ?)";
    private final static String GET_MERCHANT_BANK_ACCOUNTS = "SELECT * FROM bank_accounts WHERE merchantId = ? order by status ASC, createdAt ASC";
    private static final String DELETE_BANK_ACCOUNT_QUERY = "DELETE FROM bank_accounts WHERE merchantId = ? AND accountNumber = ?";

//    private static Connection connection;

//    private CRUDUtils() {
//        connection = DbUtils.getConnection();
//    }

    public static void createMerchant(Merchant merchant) { //создание мерчанта (1)
        try {
            Connection connection = DbUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MERCHANT_QUERY);
            preparedStatement.setString(1, merchant.getId());
            preparedStatement.setString(2, merchant.getName());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(merchant.getCreatedAt()));
            preparedStatement.executeUpdate();
            System.out.println("Мерчант добавлен в базу");
        } catch (SQLException e) {
            System.out.println("При добавлении мерчанта в базу произошла ошибка");
        }
    }

    public static List<Merchant> getAllMerchants() { //возвращает всех мерчентов (2)
        try {
            Connection connection = DbUtils.getConnection();
            ResultSet set = connection.createStatement().executeQuery(GET_MERCHANTS_QUERY);
            List<Merchant> list = new ArrayList<>();
            while (set.next()) {
                list.add(new Merchant(set.getString("id"), set.getString("name"),
                        set.getTimestamp("createdAt").toLocalDateTime()));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static Merchant getMerchantById(String id) throws MerchantNotFoundException { // получение информации о мерчанте по id (3)
        try {
            Connection connection = DbUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MERCHANT_ID_QUERY);
            preparedStatement.setString(1, id);
            ResultSet set = preparedStatement.executeQuery();
            set.next();
            return new Merchant(set.getString("id"), set.getString("name"),
                    set.getTimestamp("createdAt").toLocalDateTime());
        } catch (SQLException e) {
            throw new MerchantNotFoundException("Мерчант с id " + id + " отсутствует в базе\n");
        }
    }

    public static void deleteMerchantById(String id) { // удаление мерчента (4)
        PreparedStatement preparedStatement;
        try {
            Connection connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_MERCHANT_QUERY);
            preparedStatement.setString(1, id);
            int result = preparedStatement.executeUpdate();
            if (result == 0) {
                System.out.println("Мерчант с id " + id + " отсутствует в базе\n");
                System.out.println();
            } else {
                System.out.println("Мерчент с id - " + id + " успешно удален!\n");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("При удалении мерчанта из базы произошла ошибка\n");
        }
    }

    public static void addBankAccount(BankAccount bankAccount) { // добавление нового банковского аккаунта мерчанту (5)
        try {
            Connection connection = DbUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BANK_ACCOUNT_QUERY);
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
    public static BankAccount getBankAccountById(String id) { // получение информации о банковских аккаунтах мерчента (6)

        return null;
    }


    public static List<BankAccount> getMerchantBankAccounts(String idMerchant) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MERCHANT_BANK_ACCOUNTS)) {
            preparedStatement.setString(1, idMerchant);
            ResultSet set = preparedStatement.executeQuery();
            while (set.next()) {
                AccountStatus status = (set.getString("status").equals("ACTIVE")) ?
                        AccountStatus.ACTIVE : AccountStatus.DELETED;
                bankAccounts.add(new BankAccount(set.getString("merchantId"), set.getString("id"),
//                        ConverterUtils.toAccountStatus(set.getString("status")),
                        status,
                        set.getString("accountNumber"), set.getTimestamp("createdAt").toLocalDateTime()));
            }
        } catch (SQLException e) {
            System.out.println("Банковский аккаунт не найден");;
        }


        return bankAccounts;
    }


    public BankAccount updateMerchantBankAccount(BankAccount bankAccount) {
        //Все операторы SQL должны быть описаны здесь как константы Ваш запрос к БД для обновления банковского счета
        return null;
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        //Ваш запрос в БД на сохранение нового банковского счета
        return null;
    }



    public static void deleteBankAccountById(String merchantId, String idAccount) { // удаление банковского аккаунта мерчанта (8)
        PreparedStatement preparedStatement;
        try {
            Connection connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_BANK_ACCOUNT_QUERY);
            preparedStatement.setString(1, merchantId);
            preparedStatement.setString(2, idAccount);
            int result = preparedStatement.executeUpdate();
            if (result == 0) {
                System.out.println("Банковский аккаунт с id " + idAccount + " отсутствует в базе\n");
            } else {
                System.out.println("Банковский аккаунт с id - " + idAccount + " успешно удален у мерчанта " + merchantId + "\n");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("При удалении банковского аккаунта из базы произошла ошибка\n");
        }
    }


}
