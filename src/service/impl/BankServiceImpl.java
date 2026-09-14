package service.impl;

import domain.Account;
import repository.AccountRepository;
import service.BankService;

import java.util.UUID;

public class BankServiceImpl implements BankService{
    AccountRepository accountRepository=new AccountRepository();


    @Override
    public String openAccount(String name, String email, String accountType) {
        String customerId= UUID.randomUUID().toString();
        String accountNumber=UUID.randomUUID().toString();

        Account account=new Account(accountNumber,customerId, (double) 0,accountType);
        accountRepository.save(account);
        return  accountNumber;
    }
}
