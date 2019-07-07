package ru.arhiser.funcops;

public class Client {
    private String name;
    private int balance;
    private boolean isActive;

    public Client(String name, int sum, boolean isActive) {
        this.name = name;
        this.balance = sum;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", isActive=" + isActive +
                '}';
    }
}
