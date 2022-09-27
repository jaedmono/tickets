package com.jaedmono.tickets.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = TicketCreateResponse.TicketCreateResponseBuilder.class)
@Builder(builderClassName = "TicketCreateResponseBuilder", toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketCreateResponse {

    @JsonPOJOBuilder(withPrefix = "")
    public static class TicketCreateResponseBuilder  {}
}
