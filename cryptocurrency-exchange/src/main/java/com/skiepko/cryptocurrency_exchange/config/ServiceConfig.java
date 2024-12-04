package com.skiepko.cryptocurrency_exchange.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "service")
@Data
public class ServiceConfig {

    private List<String> supportedCurrencies;

    private Long fee;

    private BigDecimal minAmount;
    private BigDecimal maxAmount;

    private Long synchronizationRate;

    private Long expirationFactor;

    private String coinGateEndpoint;


}
