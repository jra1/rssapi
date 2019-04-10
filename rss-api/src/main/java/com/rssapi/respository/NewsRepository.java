package com.rssapi.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rssapi.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{

}
