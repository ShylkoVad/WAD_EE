package by.teachmeskills.hw_12052023.service;

import by.teachmeskills.hw_12052023.exceptions.BankAccountNotFoundException;
import by.teachmeskills.hw_12052023.exceptions.MerchantNotFoundException;
import by.teachmeskills.hw_12052023.exceptions.NoBankAccountsFoundException;
import by.teachmeskills.hw_12052023.model.AccountStatus;
import by.teachmeskills.hw_12052023.model.BankAccount;
import by.teachmeskills.hw_12052023.model.Merchant;
import by.teachmeskills.hw_12052023.utils.CRUDUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MerchantService {
    static Scanner scanner = new Scanner(System.in);

    public void createMerchant(Merchant merchant) { //создание мерчанта (1)
        CRUDUtils.createMerchant(merchant);
    }

    public List<Merchant> getMerchants() { //возвращает всех мерчантов (2)
        Objects.requireNonNull(CRUDUtils.getAllMerchants()).forEach(s ->
                System.out.printf("Мерчант: ID - %s, имя - %s, дата добавления в базу - %s \n",
                        s.getId(), s.getName(), s.getCreatedAt()));
        System.out.println();
        return new ArrayList<>();
    }

    public boolean getMerchantsById(String idScanner) throws MerchantNotFoundException { // получение информации о мерчанте по id (3)
        CRUDUtils.getMerchantById(idScanner);
        return true;
    }

    public void deletedMerchant(String idDelete) { // удаление мерчанта (4)
        CRUDUtils.deleteMerchantById(idDelete);
    }

    public void addBankAccount(BankAccount bankAccount, String idScannerAccount) throws NoBankAccountsFoundException {
        // добавление нового банковского аккаунта мерчанту (5)
        if (!validateBankAccountNumber(idScannerAccount)) {
            throw new NoBankAccountsFoundException("Номер банковского аккаунта введен некорректно" + "\n");
        }
        if (bankAccount.getStatus() == AccountStatus.DELETED) {
            bankAccount.setStatus(AccountStatus.ACTIVE);
        } else {
            CRUDUtils.addBankAccount(bankAccount);
        }
    }

    public List<BankAccount> getMerchantBankAccounts(String idScanner) { // получение информации о банковских аккаунтах мерчанта (6)
        Objects.requireNonNull(CRUDUtils.getMerchantBankAccounts(idScanner)).forEach(s ->
                System.out.printf("Банк аккаунт: ID мерчанта - %s, id банковского аккаунта - %s, статус -%s," +
                                " номер аккаунта - %s, дата добавления в базу - %s\n", s.getId(), s.getMerchantId(), s.getStatus(),
                        s.getAccountNumber(), s.getCreatedAt()));
        return null;
    }

    public void updateBankAccount(String merchantId) { // редактирование банковского аккаунта мерчанта (7)
        try {
            if (getMerchantsById(merchantId)) {
                getMerchantBankAccounts(merchantId);
                System.out.print("Введите номер аккаунта, который необходимо редактировать: ");
                String idScannerAccount = scanner.nextLine();
                if (validateBankAccountNumber(idScannerAccount)) {
                    System.out.print("Введите новый номер аккаунта: ");
                    String newNumber = scanner.nextLine();
                    BankAccount bankAccount = new BankAccount(merchantId, AccountStatus.ACTIVE, idScannerAccount, LocalDateTime.now());
                    switch (CRUDUtils.searchDuplicateAccount(bankAccount)) {
                        case 0 -> {
                            CRUDUtils.updateBankAccount(newNumber, merchantId, idScannerAccount);
                            System.out.println("Банковский аккаунт успешно отредактирован.\n");
                        }
                        case 1 -> System.out.println("Данный аккаунт существует.\n");
                    }
                }
            }
        } catch (BankAccountNotFoundException | NoBankAccountsFoundException | MerchantNotFoundException e) {
            System.out.printf(e.getMessage());
        }
    }

    public void deleteBankAccount(String merchantId, String accountNumber) { // удаление банковского аккаунта мерчанта (8)
        CRUDUtils.deleteBankAccountById(merchantId, accountNumber);
    }

    private static boolean validateBankAccountNumber(String bankAccount) throws NoBankAccountsFoundException {
        if (bankAccount.length() == 8 && bankAccount.matches("\\d+")) {
            return true;
        } else {
            throw new NoBankAccountsFoundException("Введен неверный номер аккаунта.\n");
        }
    }

}
