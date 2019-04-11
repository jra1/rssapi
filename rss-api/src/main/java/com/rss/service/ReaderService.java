package com.rss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rss.model.Feed;
import com.rss.respository.FeedRepository;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;

@Service
public class ReaderService {

	@Autowired
	FeedRepository feedRepository;

	public void saveFeed(SyndEntry entry) {
		Feed feed = new Feed();
		try {
			feed.setTitle(entry.getTitle());
			feed.setDescription(entry.getDescription().getValue());
			feed.setUri(entry.getUri());
			List<SyndEnclosure> l = entry.getEnclosures();
			feed.setImage(l.get(0).getUrl());
			feed.setPublishedDate(entry.getPublishedDate());
			feedRepository.save(feed);
		} catch (DataIntegrityViolationException e) {
			System.out.println("This feed is already saved : " + feed.getUri());
		} catch (Exception e) {
			System.out.println("Error saving feed... : " + e.getMessage());
		}
	}
}
