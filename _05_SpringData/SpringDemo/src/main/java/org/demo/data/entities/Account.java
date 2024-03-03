package org.demo.data.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="accounts")
public class Account extends BaseEntity {
    @Column
    private BigDecimal balance;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

    public Account(){

    }

    public Account(BigDecimal balance) {
        this.balance = balance;
    }

    public Account(BigDecimal balance, User user) {
        this.balance = balance;
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
