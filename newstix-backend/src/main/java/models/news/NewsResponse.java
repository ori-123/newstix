package models.news;

import java.util.List;

public record NewsResponse(String status, int totalResults, List<NewsItem> results) {
}
