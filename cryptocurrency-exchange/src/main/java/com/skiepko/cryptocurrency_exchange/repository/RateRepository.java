package com.skiepko.cryptocurrency_exchange.repository;

import com.skiepko.cryptocurrency_exchange.model.Rate;
import com.skiepko.cryptocurrency_exchange.model.RatePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, RatePK> {

    List<Rate> findByFromCurr(String code);

    List<Rate> findByFromCurrAndToCurrIn(String code, List<String> filterList);
}
