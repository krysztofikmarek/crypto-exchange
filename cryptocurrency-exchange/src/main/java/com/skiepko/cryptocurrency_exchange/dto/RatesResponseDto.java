package com.skiepko.cryptocurrency_exchange.dto;

import com.skiepko.cryptocurrency_exchange.model.Rate;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class RatesResponseDto {

    private String source;
    private Map<String, BigDecimal> rates;

    public static RatesResponseDto fromRates(List<Rate> ratesByCode) {
        validateAllRatesFromSameCurrency(ratesByCode);
        return RatesResponseDto.builder()
                .source(ratesByCode.stream().findAny().get().getFromCurr())
                .rates(ratesByCode.stream().collect(Collectors.toMap(Rate::getToCurr, Rate::getRate)))
                .build();
    }

    private static void validateAllRatesFromSameCurrency(List<Rate> ratesByCode) {
        if(ratesByCode.stream().map(Rate::getFromCurr).distinct().count() != 1L){
            throw new IllegalArgumentException("All rates must be from same currency");
        }
    }

}
