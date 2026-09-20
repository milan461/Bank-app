package repository;

import domain.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionRepository {
    private static final Map<String, List<Transaction>>txByAccount=new HashMap<>();

    public static void add(Transaction transaction) {
        List <Transaction>list=txByAccount.computeIfAbsent(transaction.getAccountNumber(),
                k->new ArrayList<>());
        list.add(transaction);
    }
}
