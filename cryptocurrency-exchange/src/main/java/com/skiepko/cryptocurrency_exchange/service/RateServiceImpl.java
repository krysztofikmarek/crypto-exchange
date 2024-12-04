package com.skiepko.cryptocurrency_exchange.service;

import com.skiepko.cryptocurrency_exchange.repository.RateRepository;
import com.skiepko.cryptocurrency_exchange.model.Rate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;
    public List<Rate> getRatesByCode(String code, List<String> filter) {
        log.info("Getting rates by for: {} and filter: {}", code, filter);
        if(filter != null && filter.size() > 0){
            return rateRepository.findByFromCurrAndToCurrIn(code, filter);
        }else {
            return rateRepository.findByFromCurr(code);
        }
    }

    @Transactional
    public void updateRate(Rate rate){
        log.info("Updating rate: {}", rate);
        rateRepository.save(rate);
    }
}
