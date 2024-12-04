package com.skiepko.cryptocurrency_exchange.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
public class RatePK implements Serializable {
    private String fromCurr;
    private String toCurr;

    public RatePK() {

    }

}
