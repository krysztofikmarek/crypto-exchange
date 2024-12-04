package com.skiepko.cryptocurrency_exchange.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.skiepko.cryptocurrency_exchange.model.ExchangeResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
public class ExchangeResponseDto {

    @Getter
    @Setter
    private String from;


    private Map<String, ExchangeDto> exchanges;

    public static ExchangeResponseDto fromResultList(List<ExchangeResult> result) {
        validateAllRatesFromSameCurrency(result);
        return ExchangeResponseDto.builder()
                .from(result.stream().findAny().get().getFromCurr())
                .exchanges(result.stream().collect(Collectors.toMap(ExchangeResult::getToCurr, ExchangeDto::fromExchangeResult)))
                .build();
    }

    private static void validateAllRatesFromSameCurrency(List<ExchangeResult> result) {
        if(result.stream().map(ExchangeResult::getFromCurr).distinct().count() != 1L){
            throw new IllegalArgumentException("All exchanges must be from same currency");
        }
    }

    @JsonAnyGetter
    public Map<String, ExchangeDto> getExchanges(){
        return exchanges;
    }

    @JsonAnySetter
    public void setExchange(String key, ExchangeDto value){
        exchanges.put(key, value);
    }

}
