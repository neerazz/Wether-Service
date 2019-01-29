package com.neeraj.assignment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neeraj.assignment.controller.ApiController;
import com.neeraj.assignment.model.FrontEndUserModel;
import com.neeraj.assignment.model.WeatherEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentApplicationTests {

	private MockMvc mockMVC;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private ApiController weatherController;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() {
		this.mockMVC = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void check_controller_present_in_webApplicationContext() {
		ServletContext servletContext = webApplicationContext.getServletContext();

		Assert.assertNotNull(servletContext);
		Assert.assertTrue(servletContext instanceof MockServletContext);
		Assert.assertNotNull(webApplicationContext.getBean("tokenController"));
		Assert.assertNotNull(webApplicationContext.getBean("apiController"));
		Assert.assertNotNull(webApplicationContext.getBean("mvcController"));
	}

	@Test
	public void givenHomePageURI_whenMockMVC_thenReturnsIndexJSPViewName() throws Exception {
		mockMVC.perform(get("/home"))
				.andExpect(view().name("home-page"));
	}

	@Test
	public void getWeather() throws Exception {

		WeatherEntry weatherEntry = new WeatherEntry();
		weatherEntry.setCityName("Maricopa");
		weatherEntry.setTimeZone("America/Phoenix");
		weatherEntry.setState("AZ");
		when(weatherController.getWeather(0, 85027)).thenReturn(weatherEntry);

		mockMVC.perform(get("/api/0/85027").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
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

		mockMVC.perform(get("/api/0/85027/lowest").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.city_name").value("Maricopa"))
				.andExpect(jsonPath("$.timezone").value("America/Phoenix"))
				.andExpect(jsonPath("$.state_code").value("AZ"));
	}

	@Test
	public void test_generate_token_with_incorrect_secrete_key() throws Exception {

		FrontEndUserModel userModel
				= new FrontEndUserModel()
				.setUserName("Test")
				.setUserId("Test123")
				.setSecreatKey("Test");

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/token")
				.accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userModel))
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMVC.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.FORBIDDEN.value(),result.getResponse().getStatus());
	}
}