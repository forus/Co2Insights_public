package repository;

import entity.Transaction;

import java.util.List;

public class InMemoryTransactionRepository implements TransactionRepository {

    private final List<Transaction> allTransactions;

    public InMemoryTransactionRepository(List<Transaction> allTransactions) {
        this.allTransactions = allTransactions;
    }
    @Override
    public List<Transaction> getAllTransactions() {
        return allTransactions;
    }
}
