package com.estaine.trx.repository;

import com.estaine.trx.model.User;

import java.util.Optional;

/**
 * Created by estaine on 05.12.2018.
 */
public interface ICustomUserRepository {

    Optional<User> findById(Long id);

    User save(User user);
}
