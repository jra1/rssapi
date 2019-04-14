package com.rss.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rss.respository.FeedRepository;
import com.rss.service.FeedApiService;

@RunWith(SpringRunner.class)
@WebMvcTest(FeedApiController.class)
public class FeedApiControllerTest {

	@Autowired
	private MockMvc mvc;

	@TestConfiguration
	static class FeedApiServiceTestConfiguration {

		@Bean
		public FeedApiService feedApiService() {
			return new FeedApiService();
		}
	}

	@MockBean
	FeedRepository feedRepository;

	@MockBean
	Mapper mapper;

	@Test
	public void getAllFeedsTest() throws Exception {
		mvc.perform(get("/rssapi/feed").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));

		Mockito.verify(feedRepository, Mockito.times(1)).findAll();
	}

	@Test
	public void getFeedByIdTest() throws Exception {
		mvc.perform(get("/rssapi/feed/1").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound());

		Mockito.verify(feedRepository, Mockito.times(1)).findById(Long.valueOf("1"));
	}
}