package com.skiepko.cryptocurrency_exchange.task;

import com.skiepko.cryptocurrency_exchange.client.CoinGateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ImportTask {

    @Autowired
    private CoinGateClient coinGateClient;

    @Scheduled(fixedRateString = "${service.synchronization-rate}")
    public void importRates(){
        log.info("Import rates task started");
        coinGateClient.fetchRates();
    }
}
