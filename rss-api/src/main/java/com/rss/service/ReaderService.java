package com.rss.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rss.model.Feed;
import com.rss.respository.FeedRepository;
import com.rss.util.MapUtils;
import com.sun.syndication.feed.synd.SyndEnclosure;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

@Service
public class ReaderService {

	private static final Logger logger = LoggerFactory.getLogger(ReaderService.class);
	
	@Autowired
	FeedRepository feedRepository;

	public void manageFeed(SyndFeed feed) {
		for (SyndEntry entry : MapUtils.castList(SyndEntry.class, feed.getEntries())) {
			saveEntry(entry);
		}
	}
	
	private void saveEntry(SyndEntry entry) {
		Feed feed = new Feed();
		try {
			feed.setTitle(entry.getTitle());
			feed.setDescription(entry.getDescription().getValue());
			feed.setUri(entry.getUri());
			List<SyndEnclosure> l = MapUtils.castList(SyndEnclosure.class, entry.getEnclosures());
			feed.setImage(l.get(0).getUrl());
			feed.setPublishedDate(entry.getPublishedDate());
			feedRepository.save(feed);
			logger.info(String.format("Saved feed : %s", feed.getUri()));
		} catch (DataIntegrityViolationException e) {
			logger.info(String.format("This feed is already saved : %s", feed.getUri()));
		} catch (Exception e) {
			logger.error(String.format("Error saving feed... : %s", e.getMessage()));
		}
	}
}
