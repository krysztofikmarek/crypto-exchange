package com.skiepko.cryptocurrency_exchange.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ExchangeServiceTest {

    @Autowired
    private ExchangeService exchangeService;

    @Test
    public void feeCalculationTest1(){
        BigDecimal fee = exchangeService.calculateFee(BigDecimal.valueOf(100));

        assertEquals(fee, BigDecimal.ONE.setScale(10));
    }

    @Test
    public void feeCalculationTest2(){
        BigDecimal fee = exchangeService.calculateFee(BigDecimal.valueOf(0.0000000100));

        assertEquals(fee, BigDecimal.valueOf(0.0000000001).setScale(10));
    }
}
