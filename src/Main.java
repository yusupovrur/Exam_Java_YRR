import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int choice = 0;
        int choice2 = 0;

            System.out.println("Выберите тип карты:");
            System.out.println(" 1. Дебетовая карта");
            System.out.println(" 2. Кредитная карта");

            choice=scan.nextInt();
            //Создание дебетовых карт вручную
            Card debtCard1 = new DebtCard("Иван Иванов", 11, 5000);
            Card debtCard2 = new DebtCard("Петр Петров", 22, 2000);
            ArrayList<Card> debtCardsList = new ArrayList<>(); //создали коллекцию дебетовых карт
            debtCardsList.add(debtCard1);
            debtCardsList.add(debtCard2);

            //Создание кредитных карт вручную
            Card creditCard1 = new CreditCard("Федор Федоров", 33, 10000);
            Card creditCard2 = new CreditCard("Алексей Алексеев", 44, 20000);
            ArrayList<Card> creditCardsList = new ArrayList<>(); //создали коллекцию кредитных карт
            creditCardsList.add(creditCard1);
            creditCardsList.add(creditCard2);

            switch (choice) {

                case 1:
                    System.out.println("\nДебетовые карты:");

                    for (Card d : debtCardsList) {
                        System.out.printf("\nВладелец карты: %s;\tНомер карты: %d; \tБаланс карты: %.2f руб.",d.cardHolder,d.cardNumber,d.balance);
                    }
                    //Запись потока debtCardsList в файл, список дебетовых карт
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("debtCardsList.bin"))){
                        objectOutputStream.writeObject(debtCardsList);
                    }
                    catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    System.out.println("\n\nВыберите действие:");
                    System.out.println("1.Потратить деньги");
                    System.out.println("2.Перевести деньги на кредитную карту");
                    choice2=scan.nextInt();
                    switch (choice2) {
                        case 1: //трата денег с дебетовой карты
                            debtCardsList.get(0).spendMoney(400);
                            debtCardsList.get(1).spendMoney(900);
                            for (Card d : debtCardsList) {
                                System.out.printf("\nВладелец карты: %s;\tНомер карты: %d; \tБаланс карты: %.2f руб.",d.cardHolder,d.cardNumber,d.balance);
                            }
                            //Запись потока debtCardsList в файл, баланс дебетовых карт после траты
                            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("debtCardsList.bin"))) {
                                objectOutputStream.writeObject(debtCardsList);
                            }
                            catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;
                        case 2: //Перевод денег на кредитную карту
                            debtCardsList.get(0).setAmount(1000);
                            debtCardsList.get(1).setAmount(2000);
                            debtCardsList.get(0).transferMoney(creditCardsList.get(0), 200 );
                            debtCardsList.get(1).transferMoney(creditCardsList.get(1),20 );
                            for (Card d : debtCardsList) {
                                System.out.printf("\nВладелец карты: %s;\tНомер карты: %d; \tБаланс карты: %.2f руб.",d.cardHolder,d.cardNumber,d.balance);
                            }
                            //Запись потока debtCardsList в файл, баланс дебетовых карт после перевода на кредитную карту
                            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("debtCardsList.bin"))) {
                                objectOutputStream.writeObject(debtCardsList);
                            }
                            catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            //Запись потока creditCardsList в файл, баланс кредитных карт после перевода на кредитную карту
                            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("creditCardsList.bin"))) {
                                objectOutputStream.writeObject(creditCardsList);
                            }
                            catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;
                        default:
                            System.out.println("Вы ввели неправильный символ");
                    }

                    break;
                case 2:
                    System.out.println("\nКредитные карты:");

                    for (Card c : creditCardsList) {
                        System.out.printf("\nВладелец карты: %s;\tНомер карты: %d; \tБаланс карты: %.2f руб.",c.cardHolder,c.cardNumber,c.balance);
                    }
                    //Запись потока creditCardsList в файл, список кредитных карт
                    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("creditCardsList.bin"))){
                        objectOutputStream.writeObject(creditCardsList);
                    }
                    catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    System.out.println("\n\nВыберите действие:");
                    System.out.println("1.Потратить деньги");
                    System.out.println("2.Перевести деньги на дебетовую карту");
                    choice2=scan.nextInt();
                    switch (choice2) {
                        case 1: //трата денег с кредитной карты
                            creditCardsList.get(0).spendMoney(400);
                            creditCardsList.get(1).spendMoney(900);
                            for (Card c : creditCardsList) {
                                System.out.printf("\nВладелец карты: %s;\tНомер карты: %d; \tБаланс карты: %.2f руб.",c.cardHolder,c.cardNumber,c.balance);
                            }
                            //Запись потока creditCardsList в файл, кредитные карты после траты
                            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("creditCardsList.bin"))) {
                                objectOutputStream.writeObject(creditCardsList);
                            }
                            catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;
                        case 2: //Перевод денег на дебетовую карту
                            creditCardsList.get(0).setAmount(200);
                            creditCardsList.get(1).setAmount(400);
                            creditCardsList.get(0).transferMoney(debtCardsList.get(0), 1000 );
                            creditCardsList.get(1).transferMoney(debtCardsList.get(1),400 );
                            for (Card c : creditCardsList) {
                                System.out.printf("\nВладелец карты: %s;\tНомер карты: %d; \tБаланс карты: %.2f руб.",c.cardHolder,c.cardNumber,c.balance);
                            }
                            //Запись потока creditCardsList в файл, кредитные карты после перевода на дебетовую карту
                            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("creditCardsList.bin"))) {
                                objectOutputStream.writeObject(creditCardsList);
                            }
                            catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            //Запись потока debtCardsList в файл, дебетовые карты после перевода на дебетовую карту
                            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("debtCardsList.bin"))) {
                                objectOutputStream.writeObject(debtCardsList);
                            }

                            catch (IOException ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;
                        default:
                            System.out.println("Вы ввели неправильный символ");
                    }
                    break;
                default:
                    System.out.println("Вы ввели неправильный символ");
            }
            System.out.println("\n\nВведите 0 для выхода");


        }
    }
