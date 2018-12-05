package com.estaine.trx.service;

import com.estaine.trx.model.Ticket;
import com.estaine.trx.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by estaine on 29.11.2018.
 */
@Service
public class DefaultTicketService extends DefaultBaseService<Ticket> implements TicketService {

    @Autowired
    public DefaultTicketService(TicketRepository repository) {
        super(repository);
    }
}
