package com.jaedmono.tickets.controller;

import com.jaedmono.tickets.exception.TicketProcessException;
import com.jaedmono.tickets.model.TicketCreateRequest;
import com.jaedmono.tickets.model.TicketCreateResponse;
import com.jaedmono.tickets.model.TicketResponse;
import com.jaedmono.tickets.model.TicketListResponse;
import com.jaedmono.tickets.model.TicketStatusResponse;
import com.jaedmono.tickets.model.TicketUpdateRequest;
import com.jaedmono.tickets.model.TicketUpdatedResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("v1/ticket")
public interface TicketController {

    @PostMapping("")
    @ResponseStatus(CREATED)
    TicketCreateResponse createTicket(
            @RequestHeader Map<String, String> headers,
            @RequestBody TicketCreateRequest ticketCreateRequest)
            throws TicketProcessException;

    @GetMapping("")
    @ResponseStatus(OK)
    TicketListResponse retrieveTicketList(
            @RequestHeader Map<String, String> headers)
            throws TicketProcessException;

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    TicketResponse retrieveTicket(
            @RequestHeader Map<String, String> headers, @PathVariable String id)
            throws TicketProcessException;

    @PutMapping("/ticket/{id}")
    @ResponseStatus(OK)
    TicketUpdatedResponse putInterviewAnswers(
            @RequestHeader Map<String, String> headers,
            @RequestBody TicketUpdateRequest ticketUpdateRequest,
            @PathVariable String id)
            throws TicketProcessException;

    @PutMapping("/{id}/status")
    @ResponseStatus(OK)
    TicketStatusResponse putInterviewAnswers(
            @RequestHeader Map<String, String> headers,
            @PathVariable String id)
            throws TicketProcessException;
}
