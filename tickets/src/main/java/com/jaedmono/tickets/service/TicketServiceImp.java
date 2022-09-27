package com.jaedmono.tickets.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaedmono.tickets.exception.TicketCheckedException;
import com.jaedmono.tickets.exception.TicketNotFoundException;
import com.jaedmono.tickets.model.Line;
import com.jaedmono.tickets.model.Ticket;
import com.jaedmono.tickets.persistence.model.TicketEntity;
import com.jaedmono.tickets.persistence.repository.TicketEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImp implements TicketService{

    private Supplier<TicketNotFoundException> notFoundExceptionSupplier = ()->new TicketNotFoundException(HttpStatus.NOT_FOUND, TICKET_WITH_ID_NOT_FOUND);

    private Consumer<Line> sumEquals2 = line -> {if(line.getFirst() + line.getSecond() + line.getThird() == 2) line.setResult(10);};
    private Consumer<Line> allAreEquals = line -> {if(line.getFirst() == line.getSecond() && line.getSecond() == line.getThird()) line.setResult(5);};
    private Consumer<Line> firstIsDifferent = line -> {if(line.getFirst() != line.getSecond() && line.getFirst() != line.getThird()) line.setResult(1);};

    public static final String TICKET_WITH_ID_NOT_FOUND = "Ticket not found ";
    private TicketEntityRepository repository;

    public TicketServiceImp(TicketEntityRepository repository){
        this.repository = repository;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        TicketEntity entity = new ObjectMapper().convertValue(ticket,TicketEntity.class);
        entity =  repository.save(entity);
        ticket.setId(entity.getId());
        return ticket;
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<TicketEntity> tickets = repository.findAll();
        return tickets.stream()
                .map(ticket -> new ObjectMapper().convertValue(ticket,Ticket.class))
                .collect(Collectors.toList());
    }

    @Override
    public Ticket getTicket(Long idTicket) {
        Optional<TicketEntity> optionalTicket = repository.findById(idTicket);
        return optionalTicket.map( ticketEntity ->  new ObjectMapper().convertValue(ticketEntity,Ticket.class))
                .orElseThrow(notFoundExceptionSupplier);
    }

    @Override
    public Ticket amendTicketLines(Ticket ticket, long idTicket) {
        Optional<TicketEntity> optionalTicket = repository.findById(idTicket);
        ticket = optionalTicket.map(ticketEntity ->  new ObjectMapper().convertValue(ticketEntity,Ticket.class))
                .orElseThrow(notFoundExceptionSupplier);
        if(ticket.isChecked()){
            throw new TicketCheckedException(HttpStatus.BAD_REQUEST, "Ticket is already checked "+idTicket);
        }
        ticket.setId(idTicket);
        repository.save(new ObjectMapper().convertValue(ticket,TicketEntity.class));
        return ticket;
    }

    @Override
    public Ticket checkTicketLines(Ticket ticket, long idTicket) {
        Optional<TicketEntity> optionalTicket = repository.findById(idTicket);
        optionalTicket.orElseThrow(notFoundExceptionSupplier);
        ticket.getLines().forEach(firstIsDifferent.andThen(allAreEquals.andThen(sumEquals2)));
        repository.save( new ObjectMapper().convertValue(ticket,TicketEntity.class));
        return ticket;
    }


}
