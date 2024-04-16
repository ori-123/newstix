package controller;

import models.news.NewsItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsItemController {

    @GetMapping("/news")
    public List<NewsItem> getNews() {

    }
}
