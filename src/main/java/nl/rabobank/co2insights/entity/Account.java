package nl.rabobank.co2insights.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String iban;
    private long balance;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @OneToMany(mappedBy = "sender")
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> incomingTransactions;

    public Account(String iban, long balance, Customer customer) {
        this.iban = iban;
        this.balance = balance;
        this.customer = customer;
    }

    public Account() {

    }


    @Transient
    public List<Transaction> getAllTransactions(){
        ArrayList<Transaction> transactionArrayList = new ArrayList<>();
        transactionArrayList.add((Transaction) incomingTransactions);
        transactionArrayList.add((Transaction) outgoingTransactions);
        transactionArrayList.sort(Comparator.comparing(Transaction::getTransactionDate));
        return transactionArrayList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Transaction> getOutgoingTransactions() {
        return outgoingTransactions;
    }

    public void setOutgoingTransactions(List<Transaction> outgoingTransactions) {
        this.outgoingTransactions = outgoingTransactions;
    }

    public List<Transaction> getIncomingTransactions() {
        return incomingTransactions;
    }

    public void setIncomingTransactions(List<Transaction> incomingTransactions) {
        this.incomingTransactions = incomingTransactions;
    }
}
