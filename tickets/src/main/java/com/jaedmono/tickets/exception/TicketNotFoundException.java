package com.jaedmono.tickets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class TicketNotFoundException extends HttpClientErrorException {
    public TicketNotFoundException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
