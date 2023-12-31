package nl.rabobank.co2insights.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import nl.rabobank.co2insights.services.CategoryService;

import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_bank_account_id")
    @JsonIgnore
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_bank_account_id")
    @JsonIgnore
    private Account receiver;
    private String sourceAccountIban;
    private String counterParty;
    private long amount;
    private LocalDateTime transactionDate;

    public Transaction(String sourceAccountIban, String counterParty, long amount, LocalDateTime transactionDate) {
        this.sourceAccountIban = sourceAccountIban;
        this.counterParty = counterParty;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Transaction() {

    }
    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public void setSourceAccountIban(String sourceAccountIban) {
        this.sourceAccountIban = sourceAccountIban;
    }

    public void setCounterParty(String counterParty) {
        this.counterParty = counterParty;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCounterParty() {
        return counterParty;
    }

    public CategoryService.Category getCategory() {
        return CategoryService.categorize(this);
    }

    public String getSourceAccountIban() {
        return sourceAccountIban;
    }

    public long getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public long getCo2Emission() {
        return switch (getCategory()) {
            case Furniture, General, CarRental, DrugStores, TravelAgencies -> amount * 105;
            case Energy -> amount * 604;
            case Clothes -> amount * 1143;
            case AirTravel -> amount * 1572;
            case Groceries, BakeriesAndCafes, Catering, ConvenienceStores -> amount * 802;
            case PublicTransport -> amount * 83;
            case LodgingAndAccommodation -> amount * 211;
            case ServiceStations -> amount * 955;
            case TaxiAndLimousines -> amount * 33;
            case TrainTravel -> amount * 14;
            case OnlineShopping -> amount * 247;

            default -> 0;
        };
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sourceAccountIban='" + sourceAccountIban + '\'' +
                ", counterparty='" + counterParty + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}

