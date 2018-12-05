package com.estaine.trx.service;

import com.estaine.trx.model.BaseModel;

/**
 * Created by estaine on 29.11.2018.
 */
public interface BaseService<T extends BaseModel> {

    T create(T entity);

    T read(Long id);

    T update(Long id, T entity);

    void delete(T entity);
}
