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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimetamp() {
        return timetamp;
    }

    public void setTimetamp(LocalDateTime timetamp) {
        this.timetamp = timetamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
