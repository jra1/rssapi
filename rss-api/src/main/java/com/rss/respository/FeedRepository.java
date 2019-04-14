package com.rss.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rss.model.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long>{
	
	public Feed findByUri(String uri);
	
}
