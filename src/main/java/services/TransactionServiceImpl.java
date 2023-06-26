package services;

import entity.Transaction;
import repository.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;

    private String myIban;

    public TransactionServiceImpl(TransactionRepository transactionRepository, String myIban) {
        this.transactionRepository = transactionRepository;
        this.myIban = myIban;
    }

    @Override
    public List<Transaction> getMyTransactions() {
        return transactionRepository.getAllTransactions().stream().filter(transaction -> transaction.getSourceAccountIban().equals(myIban)).collect(Collectors.toList());
    }

}
