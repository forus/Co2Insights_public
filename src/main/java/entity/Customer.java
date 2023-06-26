package entity;

import java.util.List;

public class Customer {
    private String customerName;
    private String email;
    private String phone;
    private String address;

    private List<Account> account;

    public String getEmail() {
        return email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public List<Account> getAccount() {
        return account;
    }
}
