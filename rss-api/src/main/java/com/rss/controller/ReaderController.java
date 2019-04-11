package com.rss.controller;

import java.net.URL;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.rss.service.ReaderService;
import com.rss.util.ConfigUtils;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Controller
public class ReaderController {

	@Autowired
	ReaderService readerService;

	@Autowired
	ConfigUtils configUtil;

	@Scheduled(fixedRateString = "${fixedRate.milliseconds}")
	public void checkTheFeeds() {
		URL feedSource;
		try {
			feedSource = new URL(configUtil.getProperty("feed.url"));
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedSource));
			for (SyndEntry entry : (ArrayList<SyndEntry>) feed.getEntries()) {
				readerService.saveFeed(entry);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
