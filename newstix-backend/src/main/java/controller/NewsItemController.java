package controller;

import models.news.NewsItem;
import models.news.NewsResponse;
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

    @GetMapping("/api/news")
    public List<NewsItem> getNews(@RequestParam String language, @RequestParam String country, @RequestParam String category) {
        String apiUrl = "https://newsdata.io/api/1/news";
        String url = String.format("%s?apikey=%s&language=%s&country=%s&category=%s",
                apiUrl, apiKey, language, country, category);

        NewsResponse response = restTemplate.getForObject(url, NewsResponse.class);
        return response != null ? response.getResults() : List.of();
    }
}
