package com.skiepko.cryptocurrency_exchange.client;


import com.skiepko.cryptocurrency_exchange.config.ServiceConfig;
import com.skiepko.cryptocurrency_exchange.dto.CoinGateRateResponseDto;
import com.skiepko.cryptocurrency_exchange.model.Rate;
import com.skiepko.cryptocurrency_exchange.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.Map;

@Component
public class CoinGateClient {

    @Autowired
    private WebClient client;

    @Autowired
    private ServiceConfig serviceConfig;

    @Autowired
    private RateService rateService;

    public void fetchRates() {

        client.get()
                .uri(serviceConfig.getCoinGateEndpoint())
                .retrieve()
                .bodyToMono(CoinGateRateResponseDto.class)
                .subscribe(this::updateRates);
    }

    private void updateRates(CoinGateRateResponseDto rates) {
        rates.getCurrenciesRates()
                .entrySet()
                .stream()
                .filter(this::isCrypto)
                .flatMap(curr -> curr.getValue().getRates().entrySet().stream()
                        .filter(this::isCrypto)
                        .map(r -> new Rate(curr.getKey(), r.getKey(), r.getValue(), Instant.now())))
                .forEach(rate -> rateService.updateRate(rate));
    }


    private boolean isCrypto(Map.Entry<String, ?> stringBigDecimalEntry) {
        return this.isCrypto(stringBigDecimalEntry.getKey());
    }

    private boolean isCrypto(String code) {
        return serviceConfig.getSupportedCurrencies().contains(code);
    }
}
