package services;

import entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repository.TransactionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getTransactionsByIban(String iban) {
        //TODO Check whether current user is allowed to see transactions for this IBAN
        return transactionRepository.getAllTransactions().stream().filter(transaction -> transaction.getSourceAccountIban().equals(iban)).collect(Collectors.toList());
    }

}
