package com.neeraj.assignment;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.neeraj.assignment.controller.APIController;
import com.neeraj.assignment.model.WeatherEntry;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentApplicationTests {

	@Autowired
	private MockMvc mockMVC;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private APIController weatherController;

	@Before
	public void setUp() {
		this.mockMVC = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getWeather() throws Exception {

		WeatherEntry weatherEntry = new WeatherEntry();
		weatherEntry.setCityName("Maricopa");
		weatherEntry.setTimeZone("America/Phoenix");
		weatherEntry.setState("AZ");
		when(weatherController.getWeather(0, 85027)).thenReturn(weatherEntry);

		mockMVC.perform(get("/api/weather/0/hourly/85027").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.city_name").value("Maricopa"))
				.andExpect(jsonPath("$.timezone").value("America/Phoenix"))
				.andExpect(jsonPath("$.state_code").value("AZ"));

	}

	@Test
	public void getLowestWeather() throws Exception {

		WeatherEntry weatherEntry = new WeatherEntry();
		weatherEntry.setCityName("Maricopa");
		weatherEntry.setTimeZone("America/Phoenix");
		weatherEntry.setState("AZ");
		when(weatherController.getLowestWeather(0, 85027)).thenReturn(weatherEntry);

		mockMVC.perform(get("/api/weather/0/lowest/85027").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.city_name").value("Maricopa"))
				.andExpect(jsonPath("$.timezone").value("America/Phoenix"))
				.andExpect(jsonPath("$.state_code").value("AZ"));

	}
}