package com.neeraj.assignment.service;

import java.net.URI;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.neeraj.assignment.exception.InvalidDateInRequest;
import com.neeraj.assignment.exception.InvalidZipCode;
import com.neeraj.assignment.model.HourlyWeather;
import com.neeraj.assignment.model.WeatherEntry;

@Service
@PropertySource({ "classpath:application.properties" })
public class WeatherService {

	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
	public static final String WETHER_URL = "https://api.weatherbit.io/v2.0/forecast/hourly?postal_code={zipCode}&country=US&key={apiKey}&hours=48";
	private final RestTemplate restTemplate;
	private final String weatherApiKey;

	@Autowired
	public WeatherService(RestTemplateBuilder restTemplateBuilder, @Value("${weather.app.key}") String weatherApiKey) {
		this.restTemplate = restTemplateBuilder.build();
		this.weatherApiKey = weatherApiKey;
	}

	/*
	 * This method is to extracts the weather data of last 48 hours.
	 */
	private WeatherEntry getLast48Hours(Integer zipCode) {
		logger.trace("getLast48Hours method for ZipCode: {}.", zipCode);

		URI url = new UriTemplate(WETHER_URL).expand(zipCode, this.weatherApiKey);

		if (!invoke(url, WeatherEntry.class).getStatusCode().equals(HttpStatus.OK)) {
			throw new InvalidZipCode("The entered Zip Code " + zipCode + " is invalid. Enter a valid US Zip Code.");
		}
		return invoke(url, WeatherEntry.class).getBody();
	}

	/*
	 * This method is to filter out current day's weather data.
	 */
	public WeatherEntry getWeather(Integer zipCode, Integer days) {

		logger.trace("getWeather method with Zip Code:{} & days: {}.", zipCode, days);

		validateDays(days);
		validateZip(zipCode);

		return filterHourlyWeather(getLast48Hours(zipCode),
				hW -> hW.getLocalTime().toLocalDate().equals(LocalDate.now().plusDays(days)));
	}

	/*
	 * This method is to validate the input date.
	 */
	private void validateDays(Integer days) {
		if (days < 0 || days > 1) {
			throw new InvalidDateInRequest(
					"The entered days " + days + " is invalid. Number of days must be between 0 & 1.");
		}
	}

	/*
	 * This method is to validate the input zipCode.
	 */
	private void validateZip(Integer zipCode) {
		if (zipCode < 0 || zipCode > 99999 || String.valueOf(zipCode).trim().length() != 5) {
			throw new InvalidZipCode(
					"The entered Zip Code " + zipCode + " is invalid. Zip Code must be a positive 5 digit number.");
		}
	}

	/*
	 * This method is to filter out current day's weather data.
	 */
	public WeatherEntry getLowestWeather(Integer zipCode, Integer days) {

		logger.trace("getWeather method with Zip Code:{} & days: {}.", zipCode, days);

		validateDays(days);
		validateZip(zipCode);

		return filterHourlyWeather(getLast48Hours(zipCode),
				hW -> hW.getLocalTime().toLocalDate().equals(LocalDate.now().plusDays(days)),
				(hW1, hw2) -> hw2.getTemperature().compareTo(hW1.getTemperature()));
	}

	/*
	 * This method is to filter out the weather data, based on the Predicate and get
	 * the maximum.
	 */
	private WeatherEntry filterHourlyWeather(WeatherEntry wetherDump, Predicate<HourlyWeather> filterCriteria,
			Comparator<HourlyWeather> comparator) {
		logger.trace("filterHourlyWeather the hours, with Comparator");
		Optional<HourlyWeather> maxWether = wetherDump.getHourlyWeather().parallelStream().filter(filterCriteria)
				.max(comparator);
		return wetherDump.setMaxHourlyWeather(maxWether.get());
	}

	/*
	 * This method is to filter out the weather data, based on the Predicate.
	 */
	private WeatherEntry filterHourlyWeather(WeatherEntry wetherDump, Predicate<HourlyWeather> filterCriteria) {
		logger.trace("Filtering the hours.");
		return wetherDump.setHourlyWeather(
				wetherDump.getHourlyWeather().parallelStream().filter(filterCriteria).collect(Collectors.toList()));
	}

	private <T> ResponseEntity<T> invoke(URI url, Class<T> responseType) {
		logger.trace("invoke URL: {}.", url);
		RequestEntity<?> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
		return this.restTemplate.exchange(request, responseType);
	}
}
