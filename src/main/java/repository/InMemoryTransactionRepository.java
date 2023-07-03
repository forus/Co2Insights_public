package repository;

import entity.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
