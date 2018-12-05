package com.estaine.trx.controller;

import com.estaine.trx.model.User;
import com.estaine.trx.service.UserService;
import com.estaine.trx.util.RandomStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by estaine on 28.11.2018.
 */
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/update")
        public User update() {
        User user = userService.update(1L, new User(RandomStringUtils.generate(4)));
        log.info("UPDT: {}", user);
        return user;
    }

    @RequestMapping("/get")
    public User get() {
        return userService.read(1L);
    }
}
