package repository;

import domain.Account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountRepository {
    private final Map<String, Account> accountByNumber=new HashMap<>();
    public void save(Account account){
        accountByNumber.put(account.getAccountNumber(),account);

    }
    public List<Account> findAll(){
        return new ArrayList<>(accountByNumber.values());
    }
}
