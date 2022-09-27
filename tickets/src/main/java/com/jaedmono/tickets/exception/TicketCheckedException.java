package com.jaedmono.tickets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class TicketCheckedException extends HttpClientErrorException {
    public TicketCheckedException(HttpStatus statusCode, String statusText) {
        super(statusCode, statusText);
    }
}
