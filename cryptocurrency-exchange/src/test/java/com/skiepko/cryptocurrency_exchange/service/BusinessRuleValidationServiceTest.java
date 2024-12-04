package com.skiepko.cryptocurrency_exchange.service;

import com.skiepko.cryptocurrency_exchange.exception.AmountException;
import com.skiepko.cryptocurrency_exchange.exception.TimeoutException;
import com.skiepko.cryptocurrency_exchange.model.ExchangeResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class BusinessRuleValidationServiceTest {

    @Autowired
    private BusinessRuleValidationService businessRuleValidationService;

    @Test
    void expirationTimeTest() {

        ExchangeResult exchangeResult = crateExchangeWithExpiredRateTimestamp();

        assertThrows(TimeoutException.class, () -> businessRuleValidationService.validateTime(exchangeResult));
    }

    @Test
    void exchangeAmountTest() {

        ExchangeResult exchangeResult = crateExchangeWithInvalidAmount();

        assertThrows(AmountException.class, () -> businessRuleValidationService.validateAmount(exchangeResult));
    }

    private ExchangeResult crateExchangeWithExpiredRateTimestamp() {
        return ExchangeResult.builder()
                .fromCurr("BTC")
                .toCurr("LTC")
                .amount(BigDecimal.TEN)
                .rate(BigDecimal.TEN)
                .result(BigDecimal.valueOf(99))
                .fee(BigDecimal.ONE)
                .exchangeTimestamp(Instant.now().minusSeconds(1000))
                .build();
    }

    private ExchangeResult crateExchangeWithInvalidAmount() {
        return ExchangeResult.builder()
                .fromCurr("BTC")
                .toCurr("LTC")
                .amount(BigDecimal.valueOf(1000000000))
                .rate(BigDecimal.TEN)
                .result(BigDecimal.valueOf(99))
                .fee(BigDecimal.ONE)
                .exchangeTimestamp(Instant.now().minusSeconds(1000))
                .build();
    }
}
