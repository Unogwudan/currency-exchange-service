package com.unogwudan.microservices.currencyexchangeservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unogwudan.microservices.currencyexchangeservice.models.ExchangeValue;

public interface CurrencyExchangeRepository extends JpaRepository<ExchangeValue, Long> {

	ExchangeValue findByFromAndTo(String from, String to);
}
