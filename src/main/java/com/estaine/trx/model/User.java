package com.estaine.trx.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Created by estaine on 28.11.2018.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseModel {

    public User(String username) {
        this.username = username;
    }

    private String username;

    @Override
    public String toString() {
        return "ID: " + getId() + " :: " + username;
    }
}
