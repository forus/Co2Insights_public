package config;

import entity.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.InMemoryTransactionRepository;
import repository.TransactionRepository;
import services.TransactionService;
import services.TransactionServiceImpl;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class Config {
    @Bean
    TransactionRepository transactionRepository() {
        return new InMemoryTransactionRepository(
                Arrays.asList(
                        new Transaction("NL01436456457577", "H&M", 45L, LocalDateTime.now()),
                        new Transaction("NL34534546456457", "Lidl", 5L, LocalDateTime.now())
                        ));
    }

    @Bean
    TransactionService transactionService(TransactionRepository transactionRepository, String myIban) {
        return new TransactionServiceImpl(transactionRepository, myIban);
    }

    @Bean
    String myIban() {
        return "NL01436456457577";
    }
}
