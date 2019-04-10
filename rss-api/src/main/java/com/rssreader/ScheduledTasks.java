package com.rssreader;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Service
public class ScheduledTasks {
    
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
        String m = "Fixed Rate Task :: Execution Time - %s";
        String res = String.format(m, dateTimeFormatter.format(LocalDateTime.now()));
        System.out.println(res);
        
       
        URL feedSource;
		try {
			feedSource = new URL("http://feeds.nos.nl/nosjournaal?format=xml");
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedSource));
			
			System.out.println("Feed Title: " + feed.getTitle());
			
			for (SyndEntry message : (ArrayList<SyndEntry>) feed.getEntries()) {
	            System.out.println("Message Title: " + message.getTitle());
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
}
