package com.neeraj.assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neeraj.assignment.model.WeatherEntry;
import com.neeraj.assignment.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

	@Autowired
	private WeatherService weatherService;

	private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

	@GetMapping("/{days}/hourly/{zipCode}")
	public WeatherEntry getWeather(@PathVariable(value = "days") Integer days,
			@PathVariable(value = "zipCode") Integer zipCode) {

		logger.trace("getWeather method with Zip Code:{} & days: {}.", zipCode, days);
		return weatherService.getWeather(zipCode, days);
	}

	@GetMapping("/{days}/lowest/{zipCode}")
	public WeatherEntry getLowestWeather(@PathVariable(value = "days") Integer days,
			@PathVariable(value = "zipCode") Integer zipCode) {

		logger.trace("getLowestWeather method with Zip Code:{} & days: {}.", zipCode, days);
		return weatherService.getLowestWeather(zipCode, days);
	}
}
