package by.teachmeskills.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        List<Merchant> merchants = new ArrayList<>();
//        MerchantService merchantService = new MerchantService(merchants);
        boolean input = false;

        System.out.println("""
                Выберите необходимый пункт меню:
                1 - получение информации о банковский аккаунтах мерчанта;
                2 - добавление нового банковского аккаунта мерчанту;
                3 - редактирование банковского аккаунта мерчента;
                4 - удаление банковского аккаунта мерчанта;
                5 - получение информации о мерчанте по id;
                6 - получение всех мерчантов, зарегистрированных в платежной системе;
                7 - создание мерчанта;
                8 - удаление мерчанта;
                0 - выход;
                """);

        while (!input) {
            System.out.print("Введите число: ");
            switch (scanner.nextLine()) {
//                default -> throw new IllegalArgumentException("Неизвестный пункт меню");
                case "0" -> input = true;
                case "1" -> {
                    System.out.print("Введите id мерчента, для которого необходимо предоставить банковские аккаунт: ");
                    String idScanner = scanner.nextLine();
                }
                case "2" -> {
                    System.out.print("Введите id мерчента, которому необходимо добавить банковский аккаунт: ");
                    String idScanner = scanner.nextLine();
                }
                case "3" -> {
                    System.out.print("Введите id мерчента, у которого редактируется аккаунт: ");
                    String idScanner = scanner.nextLine();
                }
                case "4" -> {
                    System.out.print("Введите id мерчента, о которого надо удалить аккаунт: ");
                    String idScanner = scanner.nextLine();
                }
                case "5" -> {
                    System.out.print("Введите id мерчента, о котором надо получить информацию: ");
                    String idScanner = scanner.nextLine();
                }
                case "6" -> {

                }
                case "7" -> {
                    System.out.print("Введите название мерчанта: ");
                    String nameMerchantScanner = scanner.nextLine();
                }
                case "8" -> {
                    System.out.print("Введите id мерчента, которое хотите удалить: ");
                    String idDelete = scanner.nextLine();
                }

            }

        }
    }
}
