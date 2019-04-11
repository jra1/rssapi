package com.rss.reader;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.rss.model.Feed;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Controller
public class FeedReader {

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Scheduled(fixedRate = 5000)
	public void checkTheNews() {
		// String m = "Fixed Rate Task --> Execution Time - %s";
		// String res = String.format(m, dateTimeFormatter.format(LocalDateTime.now()));
		// System.out.println(res);

		URL feedSource;
		try {
			feedSource = new URL("http://feeds.nos.nl/nosjournaal?format=xml");
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedSource));

			System.out.println("Feed Title: " + feed.getTitle());
			System.out.println(feed);

			for (SyndEntry entry : (ArrayList<SyndEntry>) feed.getEntries()) {
				//System.out.println("Entry Title: " + entry.getTitle());
				saveNews(entry);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saveNews(SyndEntry entry) {
		try {
			Feed f = new Feed();
			f.setTitle(entry.getTitle());
			f.setDescription(entry.getDescription().toString());
			f.setUri(entry.getUri());
			f.setImage("");
			f.setPublicationDate(entry.getPublishedDate());
			//System.out.println(f.toMyString());
		}catch(Exception e) {
			
		}
	}
}
