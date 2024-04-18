package controller;

import models.news.NewsItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class NewsItemController {

    @Value("${newsdata.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public NewsItemController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/news")
    public List<NewsItem> getNews(@RequestParam String language, @RequestParam String country, @RequestParam String category) {
        String apiUrl = "https://newsdata.io/api/1/news";
        String apiKeyParam = "?api_key=" + apiKey;
        String queryParams = "&language=" + language + "&country=" + country + "&category=" + category;
        String url = apiUrl + apiKeyParam + queryParams;

        NewsItem[] newsItems = restTemplate.getForObject(url, NewsItem[].class);
        return List.of(newsItems);
    }
}
