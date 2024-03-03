package org.demo.service.impl;

import org.demo.data.entities.User;
import org.demo.data.repositories.UserRepository;
import org.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserById(int id) {
        Optional<User> optional = this.userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        System.out.println("No such user with given id - " + id);
        return null;

    }

    @Override
    public User findByName(String name) {
        return this.userRepository.findByUsername(name);
    }
}
