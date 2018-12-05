package com.estaine.trx.service;

import com.estaine.trx.model.User;

/**
 * Created by estaine on 29.11.2018.
 */
public interface UserService {

    User read(Long id);
    User update(Long id, User update);
}
