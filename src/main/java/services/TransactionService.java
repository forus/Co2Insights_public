package services;

import entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getMyTransactions();
}
