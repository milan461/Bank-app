package repository;

import domain.Transaction;

import java.util.*;

public class TransactionRepository {
    private static final Map<String, List<Transaction>>txByAccount=new HashMap<>();

    public static void add(Transaction transaction) {
        List <Transaction>list=txByAccount.computeIfAbsent(transaction.getAccountNumber(),
                k->new ArrayList<>());
        list.add(transaction);
    }

    public List<Transaction> findByAccount(String account) {
        return new ArrayList<>(txByAccount.getOrDefault(account,Collections.emptyList()));
    }
}
