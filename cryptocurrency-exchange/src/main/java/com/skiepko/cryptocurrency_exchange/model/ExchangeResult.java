package com.skiepko.cryptocurrency_exchange.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@AllArgsConstructor
@Data
@Builder
public class ExchangeResult {

    @Id
    @GeneratedValue
    private Long id;

    private String fromCurr;
    private String toCurr;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal result;
    private BigDecimal fee;
    private Instant exchangeTimestamp;
}
