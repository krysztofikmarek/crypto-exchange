package com.skiepko.cryptocurrency_exchange.service;

import com.skiepko.cryptocurrency_exchange.config.ServiceConfig;
import com.skiepko.cryptocurrency_exchange.exception.AmountException;
import com.skiepko.cryptocurrency_exchange.exception.TimeoutException;
import com.skiepko.cryptocurrency_exchange.model.ExchangeResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@Slf4j
public class BusinessRuleValidationServiceImpl implements BusinessRuleValidationService {

    @Autowired
    private ServiceConfig serviceConfig;

    @Override
    public void validate(ExchangeResult exchange) {
        log.info("Validating {}", exchange);
        validateAmount(exchange);
        validateTime(exchange);
    }

    @Override
    public void validateTime(ExchangeResult exchange) {
        if (calculateExpirationTimestamp().isAfter(exchange.getExchangeTimestamp())) {
            log.error("Exchange's rate expired {}", exchange);
            throw new TimeoutException("Exchange's rate expired");
        }
    }
    private Instant calculateExpirationTimestamp(){
        return Instant.now().minusMillis(serviceConfig.getExpirationFactor() * serviceConfig.getSynchronizationRate());
    }

    @Override
    public void validateAmount(ExchangeResult exchange) {
        BigDecimal amount = exchange.getAmount();
        BigDecimal min = serviceConfig.getMinAmount();
        BigDecimal max = serviceConfig.getMaxAmount();

        if (amount.compareTo(min) < 0 ||
                amount.compareTo(max) > 0 ||
                amount.compareTo(BigDecimal.ZERO) < 0) {
            log.error("Invalid amount {}", exchange);
            throw new AmountException(String.format("Amount must be between %10.10f and %10.10f, and not negative", min, max));
        }

    }
}
