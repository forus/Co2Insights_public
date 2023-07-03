package org.example;

import entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import repository.InMemoryTransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"services", "controller", "repository", "entity"})
public class Co2InsightsApplication {

    @Autowired
    InMemoryTransactionRepository inMemoryTransactionRepository;

    public static void main(String[] args) {
        SpringApplication.run(Co2InsightsApplication.class, args);

    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Transaction> inMemoryTransactions = inMemoryTransactionRepository.getAllTransactions();
        inMemoryTransactions.add(new Transaction("NL01436456457577", "HM", 45L, LocalDateTime.now()));
        inMemoryTransactions.add(new Transaction("NL01436456457577", "wizzair", 200L, LocalDateTime.now()));
        inMemoryTransactions.add(new Transaction("NL34534546456457", "Lidl", 5L, LocalDateTime.now()));
    }
}