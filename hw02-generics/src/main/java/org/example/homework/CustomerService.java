package org.example.homework;

import java.util.*;

public class CustomerService {
    private final TreeMap<Customer, String> customerMap = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> customerStringEntry = customerMap.firstEntry();
        return new AbstractMap.SimpleImmutableEntry<>(new Customer(customerStringEntry.getKey()), customerStringEntry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Customer nextCustomer = customerMap.higherKey(customer);
        return nextCustomer == null ? null : new AbstractMap.SimpleEntry<>(new Customer(nextCustomer), customerMap.get(nextCustomer));
    }

    public void add(Customer customer, String data) {
        customerMap.put(customer, data);
    }
}
