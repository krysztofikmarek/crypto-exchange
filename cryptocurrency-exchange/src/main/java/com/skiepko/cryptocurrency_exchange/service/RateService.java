package com.skiepko.cryptocurrency_exchange.service;

import com.skiepko.cryptocurrency_exchange.model.Rate;

import java.util.List;

public interface RateService {

    List<Rate> getRatesByCode(String code, List<String> filter);
    void updateRate(Rate rate);
}
