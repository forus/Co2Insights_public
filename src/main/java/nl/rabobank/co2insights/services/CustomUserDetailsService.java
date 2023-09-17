package nl.rabobank.co2insights.services;

import nl.rabobank.co2insights.entity.Customer;
import nl.rabobank.co2insights.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private CustomerRepository customerRepository;

    public CustomUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.getCustomerByEmail(username);

        if (customer.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        Customer customerObject = customer.get();
        return User.withUsername(customerObject.getEmail())
                .password(customerObject.getPassword())
                .build();
    }
}