package com.rss.controller;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.rss.service.ReaderService;
import com.rss.util.ConfigUtils;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Controller
public class ReaderController {

	private static final Logger logger = LoggerFactory.getLogger(ReaderController.class);
	
	@Autowired
	ReaderService readerService;

	@Autowired
	ConfigUtils configUtil;

	@Scheduled(fixedRateString = "${fixedRate.milliseconds}")
	public void checkFeed() {
		URL feedSource;
		try {
			feedSource = new URL(configUtil.getProperty("feed.url"));
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedSource));
			readerService.manageFeed(feed);
		} catch (Exception e) {
			logger.error(String.format("Error reading feed: %s", e.getMessage()));
		}
	}
}
