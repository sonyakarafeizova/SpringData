package org.demo;

import org.demo.data.entities.Account;
import org.demo.data.entities.User;

import org.demo.service.AccountService;
import org.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    public CommandLineRunnerImpl(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = this.userService.findUserById(1);
        User user2 = this.userService.findUserById(2);
        this.accountService.withdrawMoney(BigDecimal.valueOf(50), 1);
        this.accountService.transferMoney(BigDecimal.valueOf(100), 4);


    }
}
