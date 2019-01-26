package com.neeraj.assignment.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
public class HourlyWeather implements Serializable {

	private static final long serialVersionUID = 1L;

	private LocalDateTime localTime;
	private String wetherDescription;
	private Double temperature;

	@JsonProperty("weather")
	private void unpackFromNestedWeather(Map<String, String> weather) {
		this.wetherDescription = weather.get("description");
	}

	public LocalDateTime getLocalTime() {
		return localTime;
	}

	@JsonSetter("timestamp_local")
	public HourlyWeather setLocalTime(String localTime) {
		this.localTime = LocalDateTime.parse(localTime, DateTimeFormatter.ISO_DATE_TIME);
		return this;
	}

	public Double getTemperature() {
		return temperature;
	}

	@JsonSetter("temp")
	public HourlyWeather setTemperature(Double temperature) {
		this.temperature = temperature;
		return this;
	}

	public String getWetherDescription() {
		return wetherDescription;
	}

	public HourlyWeather setWetherDescription(String wetherDescription) {
		this.wetherDescription = wetherDescription;
		return this;
	}

	@Override
	public String toString() {
		return "HourlyWeather [timestamp_local=" + localTime + ", temperature=" + temperature + ", wetherDescription="
				+ wetherDescription + "]";
	}

}