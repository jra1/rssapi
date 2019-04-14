package com.rss.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.rss.model.Feed;
import com.rss.respository.FeedRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class FeedRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private FeedRepository feedRepository;

	@Test
	public void findByUriTest() {
		Feed newFeed = new Feed();
		newFeed.setTitle("testTitle");
		newFeed.setDescription("testDescription");
		newFeed.setUri("testUri");
		entityManager.persist(newFeed);
		entityManager.flush();

		Feed found = feedRepository.findByUri(newFeed.getUri());
		assertThat(found.getUri()).isEqualTo(newFeed.getUri());
	}

}