package by.teachmeskills.hw_12052023.service;

import by.teachmeskills.hw_12052023.model.BankAccount;
import by.teachmeskills.hw_12052023.model.Merchant;

import java.util.List;

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

    }
    public List<Merchant> getMerchants() { //возвращает всех мерчентов (6)

        return null;
    }
    public Merchant getMerchantById(String idScanner) { // получение информации о мерчанте по id (5)

        return null;
    }
    public boolean deletedMerchant(String idScanner) { // удаление мерчента (8)

        return false;
    }

}
