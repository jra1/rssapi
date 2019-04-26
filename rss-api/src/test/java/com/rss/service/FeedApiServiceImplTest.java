package com.rss.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rss.model.Feed;
import com.rss.respository.FeedRepository;

@RunWith(SpringRunner.class)
public class FeedApiServiceImplTest {

	@TestConfiguration
	static class FeedApiServiceTestConfiguration {

		@Bean
		public FeedApiServiceImpl feedApiService() {
			return new FeedApiServiceImpl();
		}
	}

	@Autowired
	private FeedApiServiceImpl feedApiService;

	@MockBean
	private FeedRepository feedRepository;

	@Before
	public void setUp() {
		Feed newFeed = new Feed();
		newFeed.setTitle("testTitle");
		newFeed.setDescription("testDescription");
		newFeed.setUri("testUri");

		Mockito.when(feedRepository.findByUri(newFeed.getUri())).thenReturn(newFeed);
	}
	
	@Test
	public void getFeedByUriTest() {
	    String uri = "testUri";
	    Feed found = feedApiService.getFeedByUri(uri);
	  
	     assertThat(found.getUri()).isEqualTo(uri);
	 }
}