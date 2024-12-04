package com.skiepko.cryptocurrency_exchange.service;

import com.skiepko.cryptocurrency_exchange.config.ServiceConfig;
import com.skiepko.cryptocurrency_exchange.model.ExchangeResult;
import com.skiepko.cryptocurrency_exchange.model.Rate;
import com.skiepko.cryptocurrency_exchange.repository.ExchangeResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;

@Service
@Slf4j
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private RateServiceImpl ratesService;

    @Autowired
    private ServiceConfig serviceConfig;
    @Autowired
    private ExchangeResultRepository exchangeResultRepository;

    @Autowired
    private BusinessRuleValidationService businessRuleValidationService;

    @Value("${service.fee}")
    private BigDecimal serviceFee;

    @Transactional
    @Override
    public ExchangeResult exchange(String from, String to, BigDecimal amount) {
        log.info("Trying to exchange {} {} to {}|", amount, from, to);
        Rate rate = ratesService.getRatesByCode(from, Collections.singletonList(to)).stream().findAny().orElseThrow(
                () -> new IllegalArgumentException("Could not get rate for given parameters")
        );
        BigDecimal rateValue = rate.getRate();
        BigDecimal result = amount.multiply(rateValue);
        BigDecimal fee = calculateFee(result);
        BigDecimal finalResult = result.subtract(fee);

        ExchangeResult exchange = ExchangeResult.builder()
                .fromCurr(from)
                .toCurr(to)
                .rate(rateValue)
                .amount(amount)
                .result(finalResult)
                .fee(fee)
                .exchangeTimestamp(rate.getRateTimestamp())
                .build();

        businessRuleValidationService.validate(exchange);
        exchangeResultRepository.save(exchange);
        log.info("Exchange successful");

        return exchange;
    }

    @Override
    public BigDecimal calculateFee(BigDecimal base){
        return base.multiply(serviceFee).setScale(10, RoundingMode.CEILING);
    }
}
