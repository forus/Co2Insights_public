package org.example;

import config.Config;
import entity.Transaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.TransactionService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        TransactionService transactionService = applicationContext.getBean(TransactionService.class);
        List<Transaction> myTransactions = transactionService.getMyTransactions();
        for (Transaction transaction: myTransactions) {
            System.out.println(transaction);
        }
    }
}