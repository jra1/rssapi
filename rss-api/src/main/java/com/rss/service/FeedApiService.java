package com.rss.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rss.model.Feed;

public interface FeedApiService {
	
	public List<Feed> getAll();
	
	public Feed create(Feed feed);
	
	public Feed getFeedById(Long feedId);
	
	public Feed updateFeed(Long feedId, Feed feedDetails);
	
	public ResponseEntity<Feed> deleteFeed(Long feedId);
	
	public Feed getFeedByUri(String uri);
}
