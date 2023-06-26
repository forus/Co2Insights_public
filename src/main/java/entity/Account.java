package entity;

import org.springframework.beans.factory.annotation.Autowired;
import services.AggregationPeriod;
import services.CategoryService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {
    private String iban;
    private long balance;

    private Customer customer;

    private List<Transaction> transactions;

    public Account(String iban, long balance, Customer customer) {
        this.iban = iban;
        this.balance = balance;
        this.customer = customer;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
