package by.teachmeskills.hw_12052023.utils;

import by.teachmeskills.hw_12052023.exceptions.MerchantNotFoundException;
import by.teachmeskills.hw_12052023.model.BankAccount;
import by.teachmeskills.hw_12052023.model.Merchant;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
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

    private final static String ADD_BANK_ACCOUNT_QUERY = "INSERT INTO bank_accounts (id, merchant_id, status, account_number, created_at) Values (?, ?, ?, ?, ?)";
    private final static String GET_MERCHANT_BANK_ACCOUNTS = "SELECT * FROM bank_accounts WHERE merchant_id = ? order by status ASC, created_at ASC";

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
            throw new MerchantNotFoundException("Мерчант с id " + id + " отсутствует в базе\n" + "\n");
        }
    }

    public static void deleteMerchantById(String id) { // удаление мерчента (4)
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DbUtils.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_MERCHANT_QUERY);
            preparedStatement.setString(1, id);
            preparedStatement.execute();
            int result = preparedStatement.executeUpdate();
            if (result == 0) {
                System.out.println("Мерчант с id " + id + " отсутствует в базе");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("При удалении мерчанта из базы произошла ошибка");
        }
    }




    public List<BankAccount> getMerchantBankAccounts(Merchant merchant) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        try (Connection connection = DbUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_MERCHANT_BANK_ACCOUNTS)) {
            statement.setString(1, merchant.getId());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                bankAccounts.add(new BankAccount(set.getString(1), set.getString(2), ConverterUtils.toAccountStatus(set.getString(3)),
                        set.getString(4), set.getTimestamp(5).toLocalDateTime()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

    public BankAccount getBankAccountById(String id) {
        //Ваш запрос к БД на получение BankAccount по его id
        return null;
    }

    public void deleteBankAccountById(String id) {
        //Ваш запрос в БД на удаление банковского счета
    }





}
