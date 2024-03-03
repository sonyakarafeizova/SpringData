package org.demo.service;

import org.demo.data.entities.Account;

import java.math.BigDecimal;

public interface AccountService {

    void createAccount(Account account);
    void withdrawMoney(BigDecimal money, int id);
    void transferMoney(BigDecimal money, int id);
}
