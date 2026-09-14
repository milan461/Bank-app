package app;

import service.BankService;
import service.impl.BankServiceImpl;

import java.util.Scanner;

//import static java.lang.StringUTF16.trim;

///
public class Main {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to console bank");
        BankService bankService=new BankServiceImpl();
        boolean runnig = true;
        while (runnig) {
            System.out.println("""
                                    1) Open Account
                                    2) Deposit
                                    3) Withdraw
                                    4) Transfer
                                    5) Account Statement
                                    6) List Accounts
                                    7) Search Accounts by Customer Name
                                    0) Exit
                    """);
            System.out.println("CHOOSE:");
            String choice = scanner.nextLine().trim();
            System.out.println("CHOICE :" + choice);

            switch (choice) {
                case "0" -> runnig = false;
                case "1" -> openAccount(scanner,bankService);

                case "2" -> deposite(scanner);
                case "3" -> withdraw(scanner);
                case "4" -> transfer(scanner);
                case "5" -> statement(scanner);
                case "6" -> listStatements(scanner);
                case "7" -> searchAccount(scanner);
            }
        }
    }
            private static void openAccount (Scanner scanner ,BankService bankservice){
                System.out.println("Cutomer Name: ");
                String name =scanner.nextLine().trim();
                System.out.println("customer Email: ");
                String email=scanner.nextLine().trim();
                System.out.println("Accunt Type(Current/Saving : ");
                String acctype=scanner.nextLine().trim();
                System.out.println("Intial Deposit: ");
                String depositStr=scanner.nextLine().trim();
                Double initial=Double.valueOf(depositStr) ;
                bankservice.openAccount(name,email,acctype);
            }

            private static void deposite (Scanner scanner){

            }

            private static void withdraw (Scanner scanner){

            }

            private static void transfer (Scanner scanner){

            }

            private static void statement (Scanner scanner){

            }


            private static void listStatements (Scanner scanner){
            }

            private static void searchAccount (Scanner scanner){
            }
         }


