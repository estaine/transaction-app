package com.estaine.trx.repository;

import com.estaine.trx.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by estaine on 29.11.2018.
 */
@Repository
public interface UserRepository extends BaseRepository<User> {

    @Transactional(propagation = Propagation.MANDATORY)
    Optional<User> findById(Long id);

    @Transactional(propagation = Propagation.MANDATORY)
    User save(User user);
}
