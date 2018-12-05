package com.estaine.trx.service;

import com.estaine.trx.model.BaseModel;
import com.estaine.trx.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by estaine on 29.11.2018.
 */
public abstract class DefaultBaseService<T extends BaseModel> implements BaseService<T> {

    protected final BaseRepository<T> repository;

    @Autowired
    public DefaultBaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    public T read(Long id) {
        repository.flush(); return repository.getOne(id);
    }

    @Override
    public T update(Long id, T entity) {
        entity.setId(id);
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }
}
