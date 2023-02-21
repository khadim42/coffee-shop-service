package com.kh.coffeeshopservice.service;


import com.kh.coffeeshopservice.exception.UsernameTakenException;
import com.kh.coffeeshopservice.model.User;
import com.kh.coffeeshopservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }


    public User findById(Long id) {
        return userRepository.findById(id).get();
    }


    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }

    public User createNewUser(User user) throws UsernameTakenException {
        String username = user.getUserName();


        if (findByUsername(username) != null) {
            throw new UsernameTakenException("Username is already taken: " + username);
        }

        return saveUser(user);
    }
}
