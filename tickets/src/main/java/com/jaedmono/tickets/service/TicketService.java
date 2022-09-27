package com.jaedmono.tickets.service;


import com.jaedmono.tickets.exception.TicketProcessException;
import com.jaedmono.tickets.model.Ticket;

import java.util.List;

public interface TicketService {

    Ticket createTicket(Ticket ticket);

    List<Ticket> getAllTickets();

    Ticket getTicket(Long idTicket) ;

    Ticket amendTicketLines(Ticket ticket, long idTicket);

    Ticket checkTicketLines(Ticket ticket, long idTicket);

}
