package nl.rabobank.co2insights.services;

import nl.rabobank.co2insights.entity.Transaction;
import org.springframework.stereotype.Service;
import nl.rabobank.co2insights.repository.TransactionRepository;

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
