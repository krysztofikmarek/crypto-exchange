package com.skiepko.cryptocurrency_exchange.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ExchangeRequestDto {
    private String from;
    private List<String> to;
    private BigDecimal amount;
}
