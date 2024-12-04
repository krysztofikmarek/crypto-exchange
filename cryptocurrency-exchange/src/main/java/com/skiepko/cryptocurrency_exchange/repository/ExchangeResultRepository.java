package com.skiepko.cryptocurrency_exchange.repository;

import com.skiepko.cryptocurrency_exchange.model.ExchangeResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeResultRepository extends JpaRepository<ExchangeResult, Long> {
}
