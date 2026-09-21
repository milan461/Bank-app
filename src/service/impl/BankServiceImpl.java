package service.impl;

import domain.Account;
import domain.Customer;
import domain.Transaction;
import domain.Type;
import repository.AccountRepository;
import repository.CustomerRepository;
import repository.TransactionRepository;
import service.BankService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Locale.filter;

public class BankServiceImpl implements BankService{
    private final AccountRepository accountRepository=new AccountRepository();
    private final TransactionRepository transactionRepository=new TransactionRepository();
    private final CustomerRepository customerRepository=new CustomerRepository();


    @Override
    public String openAccount(String name, String email, String accountType,Double initial) {
        String customerId= UUID.randomUUID().toString();
        Customer c=new Customer(customerId,name, email);
        customerRepository.save(c);
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

    @Override
    public void widthdraw(String accountNumber, Double amount, String note) {
        Account account= accountRepository.findByNumber(accountNumber)
                .orElseThrow(()->new RuntimeException("Account Number not found: "+accountNumber));
        if(account.getBalance().compareTo(amount)<0)
            throw new RuntimeException("Insufficient Balance");
        account.setBalance(account.getBalance() - amount);
        Transaction transaction=new Transaction( UUID.randomUUID().toString(), Type.WITHDRAW,account.getAccountNumber()
                ,amount,LocalDateTime.now(),note);

        TransactionRepository.add(transaction);
    }

    @Override
    public void transfer(String fromAcc, String toAcc, Double amount, String note) {
          if(fromAcc.equals(toAcc))throw new RuntimeException("Cannot transfer to your account");
        Account from= accountRepository.findByNumber(fromAcc)
                .orElseThrow(()->new RuntimeException("Account Number not found: "+fromAcc));
        Account to= accountRepository.findByNumber(toAcc)
                .orElseThrow(()->new RuntimeException("Account Number not found: "+toAcc));
        if(from.getBalance().compareTo(amount)<0)
            throw new RuntimeException("Insufficient Balance");
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        Transaction fromTransaction=new Transaction( UUID.randomUUID().toString(), Type.TRANSFER_OUT,from.getAccountNumber()
                ,amount,LocalDateTime.now(),note);
        Transaction toTransaction=new Transaction( UUID.randomUUID().toString(), Type.TRANSFER_IN,to.getAccountNumber()
                ,amount,LocalDateTime.now(),note);

        TransactionRepository.add(fromTransaction);
        TransactionRepository.add(toTransaction);
    }

    @Override
    public List<Transaction> getStatement(String accountNumber) {
        return transactionRepository.findByAccount(accountNumber).stream()
                .sorted(Comparator.comparing(Transaction::getTimestamp))
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> searchAccountByName(String q) {
        String query =(q==null)?"":q.toLowerCase();
//        List<Account>result=new ArrayList<>();
//        for(Customer c:customerRepository.findAll()) {
//            if (c.getName().toLowerCase().contains(query))
//                result.addAll(accountRepository.findByCustomerId(c.getId()));
//        }
//        result.sort(Comparator.comparing(Account::getAccountNumber));
//        return result;
        return customerRepository.findAll().stream()
        .filter(c -> c.getName().toLowerCase().contains(query))
                .flatMap(c -> accountRepository.findByCustomerId(c.getId()).stream())
                .sorted(Comparator.comparing(Account::getAccountNumber))
                .collect(Collectors.toList());
    }

    @Override
    public String getAccountNumber(){
        int size=accountRepository.findAll().size()+1;
        String accountNumber=String.format("AC%06d",size);
        return accountNumber;

    }
}
