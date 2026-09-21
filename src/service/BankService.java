package service;

import domain.Account;
import domain.Transaction;

import java.util.List;
import java.util.Map;

public interface BankService {
   String openAccount(String name,String email,String accountType,Double initial);
   List<Account>ListAccount();

   void deposit(String accountNumber, Double amount, String note);


   void widthdraw(String accountNumber, Double amount, String withdrawal);

   void transfer(String from, String to, Double amount, String transferSuccessfully);

   List<Transaction> getStatement(String account);

   List<Account> searchAccountByName(String q);

   String getAccountNumber();
}
