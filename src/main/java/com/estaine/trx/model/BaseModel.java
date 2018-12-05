package com.estaine.trx.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by estaine on 28.11.2018.
 */
@MappedSuperclass
@Data
public abstract class BaseModel {

    @Id
    @GeneratedValue
    private Long id;
}
