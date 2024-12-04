package com.skiepko.cryptocurrency_exchange.service;

import com.skiepko.cryptocurrency_exchange.model.ExchangeResult;

import java.math.BigDecimal;

public interface ExchangeService {
    ExchangeResult exchange(String from, String to, BigDecimal amount);

    BigDecimal calculateFee(BigDecimal base);
}
