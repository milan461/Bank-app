package app;

import domain.Account;
import service.BankService;
import service.impl.BankServiceImpl;

import java.util.List;
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
                case "2" -> deposit(scanner,bankService);
                case "3" -> withdraw(scanner,bankService);
                case "4" -> transfer(scanner,bankService);
                case "5" -> statement(scanner);
                case "6" -> listAccount(scanner,bankService);
                case "7" -> searchAccount(scanner);
            }
        }
    }
            private static void openAccount (Scanner scanner ,BankService bankService){
                System.out.println("Cutomer Name: ");
                String name =scanner.nextLine().trim();
                System.out.println("customer Email: ");
                String email=scanner.nextLine().trim();
                System.out.println("Accunt Type(Current/Saving) : ");
                String acctype=scanner.nextLine().trim();
                System.out.println("Intial Deposit: ");
                String depositStr=scanner.nextLine().trim();
                Double initial=Double.valueOf(depositStr) ;
                //bankService.openAccount(name,email,acctype,initial);
                String accountNumber = bankService.openAccount(name, email, acctype, initial);

                if(initial>0){
                    bankService.deposit(accountNumber, initial, "Initial Deposit");
                }

                System.out.println("Account created successfully!");
                System.out.println("Your account number is: " + accountNumber);
            }

            private static void deposit(Scanner scanner,BankService bankService ){
                System.out.println("Account Number : ");
               String accountNumber= scanner.nextLine().trim();
                System.out.println("Amount: ");
                Double amount= Double.valueOf(scanner.nextLine().trim());

                bankService.deposit(accountNumber,amount,"deposited");
                System.out.println("Deposited successfully");
                //String depositAmount=scanner.nextLine().trim();
               // System.out.println(depositAmount);
            }

            private static void withdraw (Scanner scanner, BankService bankService){
                System.out.println("Account Number : ");
                String accountNumber= scanner.nextLine().trim();
                System.out.println("Amount: ");
                Double amount= Double.valueOf(scanner.nextLine().trim());

                bankService.widthdraw(accountNumber,amount,"withdrawal");
                System.out.println("money widthdraw successfully");
            }

            private static void transfer (Scanner scanner , BankService bankService){
                System.out.println("From Account : ");
                String from= scanner.nextLine().trim();
                System.out.println("To Account : ");
                String to= scanner.nextLine().trim();
                System.out.println("Amount: ");
                Double amount= Double.valueOf(scanner.nextLine().trim());
                bankService.transfer(from,to,amount,"Transfer successfully");

            }

            private static void statement (Scanner scanner){

            }


            private static void listAccount(Scanner scanner,BankService bankService){
                    List<Account> accounts=bankService.ListAccount();
                    for(Account account:accounts){
                        System.out.println(account.getAccountNumber());
                    }
            }

            private static void searchAccount (Scanner scanner){
            }
         }


