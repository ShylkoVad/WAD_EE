package by.teachmeskills.hw_12052023.service;

import by.teachmeskills.hw_12052023.exceptions.MerchantNotFoundException;
import by.teachmeskills.hw_12052023.exceptions.NoBankAccountsFoundException;
import by.teachmeskills.hw_12052023.model.BankAccount;
import by.teachmeskills.hw_12052023.model.Merchant;
import by.teachmeskills.hw_12052023.utils.CRUDUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

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
        try {
            Merchant temp = CRUDUtils.getMerchantById(idScanner);
            System.out.printf("Мерчант: ID - %s, имя - %s, дата добавления в базу - %s\n" + "\n",
                    temp.getId(), temp.getName(), temp.getCreatedAt());
        } catch (MerchantNotFoundException e) {
            System.out.printf(e.getMessage());
        }
        return null;
    }

    public void deletedMerchant(String idScanner) { // удаление мерчента (4)
        CRUDUtils.deleteMerchantById(idScanner);
    }

    public void addBankAccount(Merchant merchant, BankAccount bankAccount) { // добавление нового банковского аккаунта мерчанту (5)
        if (Stream.of(bankAccount).anyMatch(s -> bankAccount.getAccountNumber().length() != 8 && bankAccount.getAccountNumber().matches("^\\d+"))) {
            try {
                throw new NoBankAccountsFoundException("Номер банковского аккаунта введен некорректно");
            } catch (NoBankAccountsFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public List<BankAccount> getMerchantBankAccounts(Merchant merchant) { // получение информации о банковских аккаунтах мерчента (7)

        return null;
    }

    public BankAccount updateBankAccount(BankAccount bankAccount, String number) { // редактирование банковского аккаунта мерчанта (8)

        return bankAccount;
    }

    public boolean deleteBankAccount(BankAccount bankAccount) { // удаление банковского аккаунта мерчанта (4)

        return false;
    }


}
