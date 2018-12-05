package com.estaine.trx.service;

import com.estaine.trx.model.User;
import com.estaine.trx.repository.CustomUserRepository;
import com.estaine.trx.repository.ICustomUserRepository;
import com.estaine.trx.repository.UserRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.Thread.sleep;

/**
 * Created by estaine on 29.11.2018.
 */
@Service
@Slf4j
public class DefaultUserService implements UserService {

    private final ICustomUserRepository userRepository;

    @Autowired
    public DefaultUserService(CustomUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @SneakyThrows
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public User read(Long id) {
        User user = userRepository.findById(id).get();
        for(int i = 0; i < 10; i++) {
            log.info("READ: {}", user);
            sleep(1000);
            user = userRepository.findById(id).get();
        }
        return user;
    }

    @Override
    @SneakyThrows
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public User update(Long id, User update) {
        log.info("Entering update method for user {}", id);
        User user = userRepository.findById(id).get();

        user.setUsername(update.getUsername());
        user = userRepository.save(user);

        log.info("User {} updated, falling asleep for 5s", id);
        sleep(5000);

        return user;
    }

}
