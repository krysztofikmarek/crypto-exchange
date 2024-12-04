package com.skiepko.cryptocurrency_exchange.rest;

import com.skiepko.cryptocurrency_exchange.dto.ExchangeRequestDto;
import com.skiepko.cryptocurrency_exchange.dto.ExchangeResponseDto;
import com.skiepko.cryptocurrency_exchange.dto.RatesResponseDto;
import com.skiepko.cryptocurrency_exchange.model.ExchangeResult;
import com.skiepko.cryptocurrency_exchange.model.Rate;
import com.skiepko.cryptocurrency_exchange.service.ExchangeService;
import com.skiepko.cryptocurrency_exchange.service.RateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/currencies")
@Slf4j
public class CurrencyController {

    @Autowired
    private RateService rateService;

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/{code}")
    public RatesResponseDto getRates(@PathVariable String code, @RequestParam(required = false) List<String> filter) {
        log.info("Received rate request for: {} with filter: {}", code, filter);
        List<Rate> ratesByCode = rateService.getRatesByCode(code.toUpperCase(),
                filter.stream().map(String::toUpperCase).collect(Collectors.toList()));
        return RatesResponseDto.fromRates(ratesByCode);
    }

    @PostMapping("/exchange")
    public ExchangeResponseDto exchange(@RequestBody ExchangeRequestDto request) {
        log.info("Received exchange request of {} {} to: {}", request.getAmount(), request.getFrom(), request.getTo().stream().collect(Collectors.joining(",")));
        List<ExchangeResult> result = request.getTo().stream()
                .parallel()
                .map(to -> exchangeService.exchange(request.getFrom(), to, request.getAmount()))
                .toList();

        return ExchangeResponseDto.fromResultList(result);
    }
}
