package com.jaedmono.tickets.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaedmono.tickets.model.Line;
import com.jaedmono.tickets.model.Ticket;
import com.jaedmono.tickets.persistence.model.TicketEntity;
import com.jaedmono.tickets.persistence.repository.TicketEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TicketServiceImpTest {

    private TicketServiceImp ticketService;
    private TicketEntityRepository repository;
    private Ticket ticket;

    @BeforeEach
    public void setUp(){
        ticket = getTicket();
        repository = mock(TicketEntityRepository.class);
        ticketService = new TicketServiceImp(repository);
    }


    @Test
    public void createTicket_success(){
        TicketEntity entity = new ObjectMapper().convertValue(ticket,TicketEntity.class);
        when(repository.save(entity)).thenReturn(entity);
        Ticket response = ticketService.createTicket(ticket);
        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getLines());
        assertTrue(response.getLines().size() == 4);
        verify(repository, times(1)).save(any(TicketEntity.class));
    }

    @Test
    public void getAllTickets_success(){
        List<TicketEntity> tickets = new ArrayList<>();
        tickets.add(getTicketEntity());
        tickets.add(getTicketEntity());
        tickets.add(getTicketEntity());
        tickets.add(getTicketEntity());
        when(repository.findAll()).thenReturn(tickets);
        List<Ticket> response = ticketService.getAllTickets();
        verify(repository, times(1)).findAll();
        assertNotNull(response);
        assertTrue(response.size() == 4);
    }

    @Test
    public void checkTicketLines_success(){
        TicketEntity entity = getTicketEntity();
        when(repository.findById(1l)).thenReturn(Optional.of(entity));
        Ticket response = ticketService.checkTicketLines(ticket, 1);
        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getLines());
        assertEquals(response.getLines().size(), 4);
        assertEquals(1, response.getLines().get(0).getResult());
        assertEquals(10, response.getLines().get(1).getResult());
        assertEquals(0, response.getLines().get(2).getResult());
        assertEquals(5, response.getLines().get(3).getResult());
    }

    @Test
    public void checkTicketLines_ticket_not_found(){
        when(repository.findById(1l)).thenReturn(Optional.empty());
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ticketService.checkTicketLines(ticket, 1);
        });

        String expectedMessage = "Ticket not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void amendTicketLines_success(){
        TicketEntity entity = getTicketEntity();
        when(repository.findById(1l)).thenReturn(Optional.of(entity));
        Ticket response =ticketService.amendTicketLines(ticket, 1);
        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getLines());
        assertTrue(response.getLines().size() == 4);
    }

    private TicketEntity getTicketEntity() {
        return new ObjectMapper().convertValue(getTicket(),TicketEntity.class);
    }

    private Ticket getTicket() {
        List<Line> lines = new ArrayList();
        lines.add(Line.builder().first(1).second(2).third(0).build());
        lines.add(Line.builder().first(1).second(1).third(0).build());
        lines.add(Line.builder().first(1).second(2).third(1).build());
        lines.add(Line.builder().first(1).second(1).third(1).build());
        return Ticket.builder().lines(lines).build();
    }


}
