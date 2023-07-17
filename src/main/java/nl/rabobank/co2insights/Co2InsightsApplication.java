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
        Account account = new Account();
        account.setIban("NLRABO293864298365");
        account.setBalance(3459L);
        accountRepository.save(account);

        Customer customer = new Customer();
        customer.setFullName("Jane Doe");
        customer.setAddress("Street 123");
        customer.setEmail("abc@gmail.com");
        customer.setPhone("0612647634");
        customer.setPassword("a123456");
        customerRepository.save(customer);



        Set<Account> accounts = new HashSet<>();
        accounts.add(account);
        customer.setAccount(accounts);


    }
}