package com.skiepko.cryptocurrency_exchange.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@AllArgsConstructor
@Data
@IdClass(RatePK.class)
public class Rate {


    @Id
    private String fromCurr;
    @Id
    private  String toCurr;
    @Column(precision = 20, scale = 10)
    private  BigDecimal rate;
    private Instant rateTimestamp;
    public Rate(){

    }
}
