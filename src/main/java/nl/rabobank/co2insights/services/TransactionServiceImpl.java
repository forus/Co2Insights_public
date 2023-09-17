package nl.rabobank.co2insights.services;

import nl.rabobank.co2insights.entity.Customer;
import nl.rabobank.co2insights.entity.Transaction;
import nl.rabobank.co2insights.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import nl.rabobank.co2insights.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;
    private CustomerRepository customerRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Transaction> getTransactionsByIban(String email, String iban) {
        Optional<Customer> loggedinCustomer = customerRepository.getCustomerByEmail(email);
        if (loggedinCustomer.isEmpty()) {
            throw new IllegalArgumentException("No customer with such email: " + email);
        }
        boolean bankAccountOwner = loggedinCustomer.get().getAccount().stream().anyMatch(account -> iban.equals(account.getIban()));
        if (!bankAccountOwner) {
            throw new IllegalStateException("Current user does not have bank account with such IBAN:" + iban);
        }
        return transactionRepository
                .findAll()
                .stream()
                .filter(transaction -> transaction.getSourceAccountIban().equals(iban))
                .collect(Collectors.toList());
    }

}
