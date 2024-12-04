package com.skiepko.cryptocurrency_exchange.service;

import com.skiepko.cryptocurrency_exchange.model.ExchangeResult;

public interface BusinessRuleValidationService {

    void validate(ExchangeResult exchange);

    void validateTime(ExchangeResult exchange);

    void validateAmount(ExchangeResult exchange);
}
