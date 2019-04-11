package com.rss.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rss.exception.ResourceNotFoundException;
import com.rss.model.Feed;
import com.rss.respository.FeedRepository;

@Service
public class FeedService {

	@Autowired
	FeedRepository feedRepository;

	public List<Feed> getAll() {
		return feedRepository.findAll();
	}

	public Feed create(Feed feed) {
		return feedRepository.save(feed);
	}

	public Feed getFeedById(Long feedId) {
		return feedRepository.findById(feedId).orElseThrow(() -> new ResourceNotFoundException("Feed", "id", feedId));
	}

	public Feed updateFeed(Long feedId, Feed feedDetails) {

		Feed feed = feedRepository.findById(feedId).orElseThrow(() -> new ResourceNotFoundException("Feed", "id", feedId));

		// feed.setTitle(feedDetails.getTitle());
		// feed.setContent(feedDetails.getContent());

		Feed updatedFeed = feedRepository.save(feed);
		return updatedFeed;
	}
	
	public ResponseEntity<?> deleteFeed(Long feedId) {
		Feed feed = feedRepository.findById(feedId).orElseThrow(() -> new ResourceNotFoundException("Feed", "id", feedId));

		feedRepository.delete(feed);

		return ResponseEntity.ok().build();
	}
}
