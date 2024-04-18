package models.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import models.user.Country;
import models.user.Language;

import java.util.Date;

@JsonIgnoreProperties
public record NewsItem(Long id, String title, String link, String source_id, String source_icon,
                       String creator, String image_url, String video_url, String description, Date pubDate,
                       String content, Country country, Language language) {
}
