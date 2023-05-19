package by.teachmeskills.client;

import by.teachmeskills.hw_12052023.exceptions.MerchantNotFoundException;
import by.teachmeskills.hw_12052023.exceptions.NoBankAccountsFoundException;
import by.teachmeskills.hw_12052023.model.AccountStatus;
import by.teachmeskills.hw_12052023.model.BankAccount;
import by.teachmeskills.hw_12052023.model.Merchant;
import by.teachmeskills.hw_12052023.service.MerchantService;
import by.teachmeskills.hw_12052023.utils.CRUDUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Application {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        List<Merchant> merchants = new ArrayList<>();
        MerchantService merchantService = new MerchantService();
        boolean input = false;

        System.out.println("""
                Выберите необходимый пункт меню:
                1 - создание мерчанта;
                2 - получение всех мерчантов, зарегистрированных в платежной системе;
                3 - получение информации о мерчанте по id;
                4 - удаление мерчанта;
                5 - добавление нового банковского аккаунта мерчанту;         
                6 - получение информации о банковский аккаунтах мерчанта;
                7 - редактирование банковского аккаунта мерчента;
                8 - удаление банковского аккаунта мерчанта;
                0 - выход;
                """);

        while (!input) {
            System.out.print("Введите число: ");
            switch (scanner.nextLine()) {
//                default -> throw new IllegalArgumentException("Неизвестный пункт меню");
                case "0" -> input = true;
                case "1" -> {
                    System.out.print("Введите название мерчанта: ");
                    String nameMerchantScanner = scanner.nextLine();
                    String id = String.valueOf(UUID.randomUUID());
                    LocalDateTime createdAt = LocalDateTime.now();
                    Merchant merchant = new Merchant(id, nameMerchantScanner, createdAt);
                    merchantService.createMerchant(merchant);
                    System.out.println();
                }
                case "2" -> merchantService.getMerchants();
                case "3" -> {
                    System.out.print("Введите id мерчента, о котором надо получить информацию: ");
                    String idScanner = scanner.nextLine();
                    try {
                        merchantService.getMerchantById(idScanner);
                        System.out.printf("Мерчант: ID - %s, имя - %s, дата добавления в базу - %s\n\n",
                                CRUDUtils.getMerchantById(idScanner).getId(), CRUDUtils.getMerchantById(idScanner).getName(),
                                CRUDUtils.getMerchantById(idScanner).getCreatedAt());
                    } catch (MerchantNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "4" -> {
                    System.out.print("Введите id мерчента, которое хотите удалить: ");
                    String idDelete = scanner.nextLine();
                    merchantService.deletedMerchant(idDelete);
                }
                case "5" -> {
                    System.out.print("Введите id мерчента, которому необходимо добавить банковский аккаунт: ");
                    String idScanner = scanner.nextLine();
                    try {
                        Merchant merchant = merchantService.getMerchantById(idScanner);
                        System.out.print("Введите номер банковского аккаунта мерчента: ");
                        String idScannerAccount = scanner.nextLine();
                        String id = String.valueOf(UUID.randomUUID());
                        BankAccount bankAccount = new BankAccount(id, idScanner, AccountStatus.ACTIVE, idScannerAccount, LocalDateTime.now());
                        merchantService.addBankAccount(merchant, bankAccount);
                    } catch (MerchantNotFoundException | NoBankAccountsFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case "6" -> {
                    System.out.print("Введите id мерчента, для которого необходимо предоставить банковские аккаунт: ");
                    String idScanner = scanner.nextLine();
                    merchantService.getMerchantBankAccounts(idScanner);
                }



                case "7" -> {
                    System.out.print("Введите id мерчента, у которого редактируется аккаунт: ");
                    String idScanner = scanner.nextLine();
                }
                case "8" -> {
                    System.out.print("Введите id мерчента, о которого надо удалить аккаунт: ");
                    String idScanner = scanner.nextLine();
                    System.out.print("Введите номер банковского аккаунта который необходимо удалить: ");
                    String idScannerAccount = scanner.nextLine();
                    merchantService.deleteBankAccount(idScanner, idScannerAccount);
                }
            }


        }

    }
}

