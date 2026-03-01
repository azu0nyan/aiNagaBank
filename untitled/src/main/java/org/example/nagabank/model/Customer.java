package org.example.nagabank.model;

/**
 * Represents a customer visiting the NAGA-BANK
 */
public class Customer {
    private String customerId;
    private String name;
    private CustomerType type;

    public Customer(String customerId, String name, CustomerType type) {
        this.customerId = customerId;
        this.name = name;
        this.type = type;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
