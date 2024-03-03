package org.demo.service.impl;

import org.demo.data.entities.Account;
import org.demo.data.repositories.AccountRepository;
import org.demo.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(Account account) {
        this.accountRepository.saveAndFlush(account);
    }

    @Override
    public void withdrawMoney(BigDecimal money, int id) {
        Optional<Account> optional = this.accountRepository.findById(id);
        if (optional.isPresent()) {
            Account account = optional.get();

            if (account.getBalance().compareTo(money) >= 0) {
                account.setBalance(account.getBalance().subtract(money));
                this.accountRepository.saveAndFlush(account);
            }
        }

    }

    @Override
    public void transferMoney(BigDecimal money, int id) {
        Optional<Account> optional = this.accountRepository.findById(id);
        if (optional.isPresent()) {
            Account account = optional.get();

            if (money.doubleValue() >= 0) {
                account.setBalance(account.getBalance().subtract(money));
                this.accountRepository.saveAndFlush(account);
            }
        }
    }
}
