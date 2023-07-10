package nl.rabobank.co2insights.controller;

import nl.rabobank.co2insights.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import nl.rabobank.co2insights.services.TransactionService;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    Map<Integer, Transaction> map = new HashMap<Integer, Transaction>();

    ArrayList<Transaction> transactions = new ArrayList<>();
    Transaction transaction = new Transaction("NLRABO539285693465", "JUMBO", 800L, LocalDateTime.now());
    Transaction transaction2 = new Transaction("NLING384659346544", "ZARA", 800L, LocalDateTime.now());
    @GetMapping(value = "/transactions")
    public List<Transaction> getTransactions(@RequestParam String iban) {
        return transactionService.getTransactionsByIban(iban);
    }

    @GetMapping(value = "/transactions/{id}")
    public Transaction getTransaction(@PathVariable int id) {

        map.put(1, transaction);
        map.put(2, transaction2);
        return map.get(id);
    }

    @PostMapping(value = "/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        transactions.add(transaction);
        map.put(3, transaction);
        return transaction;
    }

}
