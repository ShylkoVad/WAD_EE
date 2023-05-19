package by.teachmeskills.hw_12052023.service;

import by.teachmeskills.hw_12052023.exceptions.MerchantNotFoundException;
import by.teachmeskills.hw_12052023.exceptions.NoBankAccountsFoundException;
import by.teachmeskills.hw_12052023.model.AccountStatus;
import by.teachmeskills.hw_12052023.model.BankAccount;
import by.teachmeskills.hw_12052023.model.Merchant;
import by.teachmeskills.hw_12052023.utils.CRUDUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MerchantService {

    public void createMerchant(Merchant merchant) { //создание мерчанта (1)
        CRUDUtils.createMerchant(merchant);
    }

    public List<Merchant> getMerchants() { //возвращает всех мерчентов (2)
        Objects.requireNonNull(CRUDUtils.getAllMerchants()).forEach(s -> {
            System.out.printf("Мерчант: ID - %s, имя - %s, дата добавления в базу - %s \n",
                    s.getId(), s.getName(), s.getCreatedAt());
        });
        System.out.println();
        return new ArrayList<>();
    }

    public Merchant getMerchantById(String idScanner) throws MerchantNotFoundException { // получение информации о мерчанте по id (3)
        CRUDUtils.getMerchantById(idScanner);
        return null;
    }

    public void deletedMerchant(String idScanner) { // удаление мерчента (4)
        CRUDUtils.deleteMerchantById(idScanner);
    }

    public void addBankAccount(Merchant merchant, BankAccount bankAccount) throws NoBankAccountsFoundException { // добавление нового банковского аккаунта мерчанту (5)
        if (bankAccount.getAccountNumber().length() != 8 || bankAccount.getAccountNumber().matches("\\D+")) {
            throw new NoBankAccountsFoundException("Номер банковского аккаунта введен некорректно" + "\n");
        }
        if (bankAccount.getStatus() == AccountStatus.DELETED) {
            bankAccount.setStatus(AccountStatus.ACTIVE);
        } else {
            CRUDUtils.addBankAccount(bankAccount);
        }
    }

    public List<BankAccount> getMerchantBankAccounts(String idScanner) { // получение информации о банковских аккаунтах мерчента (6)
        Objects.requireNonNull(CRUDUtils.getMerchantBankAccounts(idScanner)).forEach(s -> {
            System.out.printf("Банк аккаунт: ID мерчанта - %s, id банка аккаунта - %s, статус -%s," +
                    " номер аккаунта - %s, дата добавления в базу - %s\n", s.getId(), s.getMerchantId(), s.getStatus(),
                    s.getAccountNumber(), s.getCreatedAt());
        });
        System.out.println();
        return null;
    }


    public void deleteBankAccount(String merchantId, String idAccount) { // удаление банковского аккаунта мерчанта (8)
        CRUDUtils.deleteBankAccountById(merchantId, idAccount);
    }


    public BankAccount updateBankAccount(BankAccount bankAccount, String number) { // редактирование банковского аккаунта мерчанта (7)

        return bankAccount;
    }


}
