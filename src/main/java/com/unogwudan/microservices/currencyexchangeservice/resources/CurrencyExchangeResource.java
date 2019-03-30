package com.unogwudan.microservices.currencyexchangeservice.resources;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.unogwudan.microservices.currencyexchangeservice.models.ExchangeValue;
import com.unogwudan.microservices.currencyexchangeservice.repositories.CurrencyExchangeRepository;

@RestController
@EnableDiscoveryClient
public class CurrencyExchangeResource {

	@Autowired
	private Environment environment;
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeRate = currencyExchangeRepository.findByFromAndTo(from, to);
		exchangeRate.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		logger.info("{}", exchangeRate);
		
		return exchangeRate;
		
	}
}
