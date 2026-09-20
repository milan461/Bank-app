package service.impl;

import domain.Account;
import domain.Transaction;
import domain.Type;
import repository.AccountRepository;
import repository.TransactionRepository;
import service.BankService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankServiceImpl implements BankService{
    private final AccountRepository accountRepository=new AccountRepository();
    private final TransactionRepository transactionRepository=new TransactionRepository();


    @Override
    public String openAccount(String name, String email, String accountType,Double initial) {
        String customerId= UUID.randomUUID().toString();
        String accountNumber=getAccountNumber();

        Account account=new Account(accountNumber,customerId,0.0,accountType);
        accountRepository.save(account);
        return  accountNumber;

    }

    @Override
    public List<Account> ListAccount() {
        return accountRepository.findAll();//.stream().collect(Collectors.toList());
    }

    @Override
    public void deposit(String accountNumber, Double amount, String note) {
          Account account= accountRepository.findByNumber(accountNumber)
                  .orElseThrow(()->new RuntimeException("Account Number not found: "+accountNumber));
          account.setBalance(account.getBalance() + amount);
          Transaction transaction=new Transaction( UUID.randomUUID().toString(), Type.DEPOSITE,account.getAccountNumber()
                  ,amount,LocalDateTime.now(),note);

          TransactionRepository.add(transaction);
    }

    private String getAccountNumber(){
        int size=accountRepository.findAll().size()+1;
        String accountNumber=String.format("AC%06d",size);
        return accountNumber;

    }
}
