package com.jaedmono.tickets.controller;

import com.jaedmono.tickets.exception.TicketProcessException;
import com.jaedmono.tickets.model.TicketCreateRequest;
import com.jaedmono.tickets.model.TicketCreateResponse;
import com.jaedmono.tickets.model.TicketListResponse;
import com.jaedmono.tickets.model.TicketResponse;
import com.jaedmono.tickets.model.TicketStatusResponse;
import com.jaedmono.tickets.model.TicketUpdateRequest;
import com.jaedmono.tickets.model.TicketUpdatedResponse;
import com.jaedmono.tickets.service.TicketService;

import java.util.Map;

public class TicketControllerImpl implements TicketController{

    private TicketService ticketService;

    @Override
    public TicketCreateResponse createTicket(Map<String, String> headers,
                                             TicketCreateRequest ticketCreateRequest)
            throws TicketProcessException {

        ticketService.createTicket(ticketCreateRequest.getTicket());
        return null;
    }

    @Override
    public TicketListResponse retrieveTicketList(Map<String, String> headers)
            throws TicketProcessException {
        return null;
    }

    @Override
    public TicketResponse retrieveTicket(Map<String, String> headers, String id)
            throws TicketProcessException {
        return null;
    }

    @Override
    public TicketUpdatedResponse putInterviewAnswers(Map<String, String> headers, TicketUpdateRequest ticketUpdateRequest, String id)
            throws TicketProcessException {
        return null;
    }

    @Override
    public TicketStatusResponse putInterviewAnswers(Map<String, String> headers, String id) throws TicketProcessException {
        return null;
    }
}
