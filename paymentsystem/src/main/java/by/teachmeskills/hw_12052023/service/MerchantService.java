package by.teachmeskills.hw_12052023.service;

import by.teachmeskills.hw_12052023.model.BankAccount;
import by.teachmeskills.hw_12052023.model.Merchant;
import by.teachmeskills.hw_12052023.utils.CRUDUtils;

import java.util.List;
import java.util.Objects;

public class MerchantService {


    // добавление нового банковского аккаунта мерчанту (2)
    public void addBankAccount(Merchant merchant, BankAccount bankAccount) {

    }

    public List<BankAccount> getMerchantBankAccounts(Merchant merchant) { // получение информации о банковских аккаунтах мерчента (1)

        return null;
    }

    public BankAccount updateBankAccount(BankAccount bankAccount, String number) { // редактирование банковского аккаунта мерчанта (3)

        return bankAccount;
    }

    public boolean deleteBankAccount(BankAccount bankAccount) { // удаление банковского аккаунта мерчанта (4)

        return false;
    }

    public void createMerchant(Merchant merchant) { //создание мерчанта (7)
        CRUDUtils.createMerchant(merchant);
    }

    public List<Merchant> getMerchants() { //возвращает всех мерчентов (6)
        Objects.requireNonNull(CRUDUtils.getAllMerchants()).forEach(s -> {
            System.out.printf("Мерчант: UUID - %s, имя - %s, дата добавления в систему - %s \n",
                    s.getId(), s.getName(), s.getCreatedAt());
        });
        System.out.println();
        return null;
    }

    public Merchant getMerchantById(String idScanner) { // получение информации о мерчанте по id (5)

        return null;
    }

    public boolean deletedMerchant(String idScanner) { // удаление мерчента (8)

        return false;
    }

}
