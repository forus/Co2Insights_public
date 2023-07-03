package repository;

import entity.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

public interface TransactionRepository {

    List<Transaction> getAllTransactions();
}
