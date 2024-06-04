package models.news;

import java.util.List;

public class NewsResponse {
    private String status;
    private int totalResults;
    private List<NewsItem> results;

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsItem> getResults() {
        return results;
    }

    public void setResults(List<NewsItem> results) {
        this.results = results;
    }
}
