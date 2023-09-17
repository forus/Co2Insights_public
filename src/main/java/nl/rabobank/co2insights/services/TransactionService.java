package nl.rabobank.co2insights.services;

import nl.rabobank.co2insights.entity.Transaction;

import java.util.List;
public interface TransactionService {
    List<Transaction> getTransactionsByIban(String userEmail, String iban);
}
