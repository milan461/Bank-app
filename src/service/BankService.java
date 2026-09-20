package service;

import domain.Account;

import java.util.List;

public interface BankService {
   String openAccount(String name,String email,String accountType,Double initial);
   List<Account>ListAccount();

   void deposit(String accountNumber, Double amount, String note);


   void widthdraw(String accountNumber, Double amount, String withdrawal);

   void transfer(String from, String to, Double amount, String transferSuccessfully);
}
