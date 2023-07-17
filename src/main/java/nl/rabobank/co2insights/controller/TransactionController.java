package nl.rabobank.co2insights.controller;

import nl.rabobank.co2insights.entity.Transaction;
import nl.rabobank.co2insights.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import nl.rabobank.co2insights.services.TransactionService;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping(value = "/transactions")
    public List<Transaction> getTransactions(@RequestParam String iban) {
        return transactionService.getTransactionsByIban(iban);
    }

    /*
    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

     */

    @PostMapping("/transactions")
    void addUser(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
