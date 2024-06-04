package models.news;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.user.Country;
import models.user.Language;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NewsItem(
        Long id,
        String title,
        String link,
        @JsonProperty("source_id") String sourceId,
        @JsonProperty("source_icon") String sourceIcon,
        List<String> creator,
        @JsonProperty("image_url") String imageUrl,
        @JsonProperty("video_url") String videoUrl,
        String description,
        @JsonProperty("pubDate")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") Date pubDate,
        String content,
        List<Country> country,
        Language language) {
}
