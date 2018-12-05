package com.estaine.trx.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by estaine on 28.11.2018.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Ticket extends BaseModel {

    @ManyToOne
    private User reporter;

    @ManyToOne
    private User assignee;

    private String summary;

    private String description;

    private Integer priority;
}
