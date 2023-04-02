package org.example;

import java.util.Objects;

public class Customer {
    private final long id;
    private String name;
    private long scores;

    public Customer(long id, String name, long scores) {
        Objects.requireNonNull(name, "Name not be a null");
        this.id = id;
        this.name = name;
        this.scores = scores;
    }

    public Customer(Customer key) {
        this.id = key.id;
        this.name = key.name;
        this.scores = key.scores;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getScores() {
        return scores;
    }

    public void setScores(long scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scores=" + scores +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}