package repository;

import entity.Transaction;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> getAllTransactions();
}
