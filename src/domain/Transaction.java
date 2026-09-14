package domain;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private Type type;
    private String accountNumber;
    private Double amount;
    private LocalDateTime timetamp;
    private String note;

    public Transaction(String id, Type type, String accountNumber, Double amount, LocalDateTime timetamp, String note) {
        this.id = id;
        this.type = type;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.timetamp = timetamp;
        this.note = note;
    }
}
