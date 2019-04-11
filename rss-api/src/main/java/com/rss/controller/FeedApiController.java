package com.rss.controller;

import java.util.List;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rss.dto.FeedRequest;
import com.rss.dto.FeedResponse;
import com.rss.model.Feed;
import com.rss.service.FeedApiService;
import com.rss.util.MapUtils;

@RestController
@RequestMapping("/rssapi")
public class FeedApiController {

	@Autowired
	FeedApiService feedApiService;

	@Autowired
	Mapper mapper;

	// Get All Feed
	@GetMapping("/feed")
	public List<FeedResponse> getAllFeeds() {
		return MapUtils.map(mapper, feedApiService.getAll(), FeedResponse.class);
	}

	// Create a new Feed
	@PostMapping("/feed")
	public FeedResponse createFeed(@Valid @RequestBody FeedRequest feedRequest) {
		Feed feed = mapper.map(feedRequest, Feed.class);
		return mapper.map(feedApiService.create(feed), FeedResponse.class);
	}

	// Get a Single Feed
	@GetMapping("/feed/{id}")
	public FeedResponse getFeedById(@PathVariable(value = "id") Long feedId) {
		return mapper.map(feedApiService.getFeedById(feedId), FeedResponse.class);
	}

	// Update a Feed
	@PutMapping("/feed/{id}")
	public FeedResponse updateFeed(@PathVariable(value = "id") Long feedId,
			@Valid @RequestBody FeedRequest feedRequest) {
		Feed feed = mapper.map(feedRequest, Feed.class);
		return mapper.map(feedApiService.updateFeed(feedId, feed), FeedResponse.class);
	}

	// Delete a Feed
	@DeleteMapping("/feed/{id}")
	public ResponseEntity<?> deleteFeed(@PathVariable(value = "id") Long feedId) {
		return feedApiService.deleteFeed(feedId);
	}
}
