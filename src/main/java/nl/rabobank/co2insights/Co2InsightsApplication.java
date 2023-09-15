package nl.rabobank.co2insights;

import nl.rabobank.co2insights.entity.Account;
import nl.rabobank.co2insights.entity.Customer;
import nl.rabobank.co2insights.entity.Transaction;
import nl.rabobank.co2insights.repository.AccountRepository;
import nl.rabobank.co2insights.repository.CustomerRepository;
import nl.rabobank.co2insights.repository.TransactionRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Co2InsightsApplication {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(Co2InsightsApplication.class, args);

    }

    public Co2InsightsApplication(AccountRepository accountRepository, TransactionRepository transactionRepository, CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }

    /*
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Transaction> inMemoryTransactions = inMemoryTransactionRepository.getAllTransactions();
        inMemoryTransactions.add(new Transaction("NL01436456457577", "HM", 45L, LocalDateTime.now()));
        inMemoryTransactions.add(new Transaction("NL01436456457577", "wizzair", 200L, LocalDateTime.now()));
        inMemoryTransactions.add(new Transaction("NL34534546456457", "Lidl", 5L, LocalDateTime.now()));
    }

     */

    @EventListener(ApplicationReadyEvent.class)
    public void doAfterApplicationReady(){
        Customer jane = new Customer();
        jane.setFullName("Jane Doe");
        jane.setAddress("Street 123");
        jane.setEmail("abc@gmail.com");
        jane.setPhone("0612647634");
        jane.setPassword("a123456");
        customerRepository.save(jane);

        Account janeAccount = new Account();
        janeAccount.setIban("NLRABO293864298365");
        janeAccount.setBalance(3459L);
        janeAccount.setCustomer(jane);
        accountRepository.save(janeAccount);

        Customer bob = new Customer();
        bob.setFullName("Bob Smith");
        bob.setAddress("Street 231");
        bob.setEmail("bob@gmail.com");
        bob.setPhone("0634521295");
        bob.setPassword("bob123");
        customerRepository.save(bob);

        Account bobAccount = new Account();
        bobAccount.setIban("NLRAB1O93864298360");
        bobAccount.setBalance(1000L);
        bobAccount.setCustomer(bob);
        accountRepository.save(bobAccount);

        Transaction transaction1 = new Transaction();
        transaction1.setSender(janeAccount);
        transaction1.setSourceAccountIban(janeAccount.getIban());
        transaction1.setReceiver(bobAccount);
        transaction1.setCounterParty(bobAccount.getIban());
        transaction1.setAmount(100);
        transactionRepository.save(transaction1);

        Transaction transaction2 = new Transaction();
        transaction2.setSender(janeAccount);
        transaction2.setSourceAccountIban(janeAccount.getIban());
        transaction2.setCounterParty("lidl");
        transaction2.setAmount(15);
        transactionRepository.save(transaction2);
    }
}