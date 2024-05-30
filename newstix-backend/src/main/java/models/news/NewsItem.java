package models.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.user.Country;
import models.user.Language;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NewsItem(
        Long id,
        String title,
        String link,
        @JsonProperty("source_id") String sourceId,
        @JsonProperty("source_icon") String sourceIcon,
        String creator,
        @JsonProperty("image_url") String imageUrl,
        @JsonProperty("video_url") String videoUrl,
        String description,
        @JsonProperty("pubDate") Date pubDate,
        String content,
        Country country,
        Language language) {
}
