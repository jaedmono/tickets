package com.jaedmono.tickets.errorhadler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

@Slf4j
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    private static final String HTTP_SERVER_ERROR_MESSAGE =
            "Server error when processing the request";

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.error(
                "Error response with status code {} and error message {}",
                response.getStatusCode(),
                response.getStatusText());
    }

    @Override
    public void handleError(URI url, HttpMethod method, ClientHttpResponse response)
            throws IOException {
        log.error(
                "Error invoking the API {} with HttpMethod {} and error response {}",
                url,
                method,
                response.getStatusText());
        if (response.getStatusCode().is4xxClientError()) {
            throw new HttpClientErrorException(response.getStatusCode(), response.getStatusText());
        }
        throw new HttpServerErrorException(response.getStatusCode(), HTTP_SERVER_ERROR_MESSAGE);
    }
}
