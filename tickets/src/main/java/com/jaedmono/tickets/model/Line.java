package com.jaedmono.tickets.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Line {

    private long id;
    private int first;
    private int second;
    private int third;
    private int result;
}
