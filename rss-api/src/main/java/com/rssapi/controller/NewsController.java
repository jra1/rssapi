package com.rssapi.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.rssapi.exception.ResourceNotFoundException;
import com.rssapi.model.News;
import com.rssapi.respository.NewsRepository;
import com.rssreader.ScheduledTasks;

@RestController
@RequestMapping("/rssapi")
public class NewsController {

    @Autowired
    NewsRepository newsRepository;
    
    @Autowired
    ScheduledTasks task;

    // Get All News
    @GetMapping("/news")
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
    
    // Create a new News
    @PostMapping("/news")
    public News createNews(@Valid @RequestBody News news) {
        return newsRepository.save(news);
    }

    // Get a Single News
    @GetMapping("/news/{id}")
    public News getNewsById(@PathVariable(value = "id") Long newsId) {
        return newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News", "id", newsId));
    }

    // Update a News
    @PutMapping("/news/{id}")
    public News updateNews(@PathVariable(value = "id") Long newsId,
                                            @Valid @RequestBody News newsDetails) {

        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News", "id", newsId));

        //news.setTitle(newsDetails.getTitle());
        //news.setContent(newsDetails.getContent());

        News updatedNews = newsRepository.save(news);
        return updatedNews;
    }

    // Delete a New
    @DeleteMapping("/news/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable(value = "id") Long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("News", "id", newsId));

        newsRepository.delete(news);

        return ResponseEntity.ok().build();
    }
}
