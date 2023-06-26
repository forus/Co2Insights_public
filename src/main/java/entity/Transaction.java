package entity;

import services.AggregationPeriod;
import services.CategoryService;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Transaction {

    private String sourceAccountIban;
    private String counterparty;
    private long amount;
    private LocalDateTime transactionDate;

    public Transaction(String sourceAccountIban, String counterparty, long amount, LocalDateTime transactionDate) {
        this.sourceAccountIban = sourceAccountIban;
        this.counterparty = counterparty;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public String getCounterparty() {
        return counterparty;
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
                ", counterparty='" + counterparty + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}

