package com.neeraj.assignment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WeatherEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cityName;
	private String timeZone;
	private String state;
	private List<HourlyWeather> hourlyWeather = new ArrayList<>();

	public String getCityName() {
		return cityName;
	}

	@JsonSetter("city_name")
	public WeatherEntry setCityName(String cityName) {
		this.cityName = cityName;
		return this;
	}

	public String getTimeZone() {
		return timeZone;
	}

	@JsonSetter("timezone")
	public WeatherEntry setTimeZone(String timeZone) {
		this.timeZone = timeZone;
		return this;
	}

	public String getState() {
		return state;
	}

	@JsonSetter("state_code")
	public WeatherEntry setState(String state) {
		this.state = state;
		return this;
	}

	@JsonGetter("hourlydate")
	public List<HourlyWeather> getHourlyWeather() {
		return hourlyWeather;
	}

	@JsonSetter("data")
	public WeatherEntry setHourlyWeather(List<HourlyWeather> hourlyWeather) {
		this.hourlyWeather = hourlyWeather;
		return this;
	}

	public WeatherEntry setMaxHourlyWeather(HourlyWeather maxHourlyWeather) {
		this.hourlyWeather = new ArrayList<>();
		this.hourlyWeather.add(maxHourlyWeather);
		return this;
	}

	public WeatherEntry setMinHourlyWeather(HourlyWeather minHourlyWeather) {
		this.hourlyWeather = new ArrayList<>();
		this.hourlyWeather.add(minHourlyWeather);
		return this;
	}

	@Override
	public String toString() {
		return "WeatherEntry [cityName=" + cityName + ", timeZone=" + timeZone + ", state=" + state + ", hourlyWeather="
				+ hourlyWeather + "]";
	}

}
