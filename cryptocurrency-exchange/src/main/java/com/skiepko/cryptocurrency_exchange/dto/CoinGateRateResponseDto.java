package com.skiepko.cryptocurrency_exchange.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class CoinGateRateResponseDto {

    private Map<String, CoinGateRateDto> currenciesRates;

    public CoinGateRateResponseDto() {
        this.currenciesRates = new HashMap<>();
    }

    @JsonAnyGetter
    public Map<String, CoinGateRateDto> getCurrenciesRates() {
        return currenciesRates;
    }

    @JsonAnySetter
    public void setCurrenciesRates(String key, CoinGateRateDto value) {
        currenciesRates.put(key, value);
    }

}

