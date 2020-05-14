package com.g09.webshopspringboot.service;

import com.g09.webshopspringboot.domain.User;
import com.g09.webshopspringboot.repository.UserRepository;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String addUser(User user) {

        /*
        Optional<User> user1 = Optional.ofNullable(user);
        user1.ifPresent(user2 -> user2.getUserName().compareTo(user.getUserName()));
        userRepository.save(user);
        */

        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return "register";

        }
        else {
            userRepository.save(user);
            return "shop";
        }
    }

    public String verifyUser(String userName, String password) {
        if (userRepository.findByUserNameAndPassword(userName, password).isPresent()) {
            return "shop";
        }
        else {
            return "login";
        }
    }
}
