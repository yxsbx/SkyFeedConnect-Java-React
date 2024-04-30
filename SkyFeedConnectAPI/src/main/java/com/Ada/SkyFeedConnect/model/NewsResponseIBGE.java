package com.Ada.SkyFeedConnect.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a response from IBGE containing news items.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class NewsResponseIBGE {
    private NewsItem[] items;

    /**
     * Represents a single news item.
     */
    @Setter
    @Getter
    public static class NewsItem {
        @JsonProperty("editorias")
        private final String title;
        @JsonProperty("titulo")
        private final String subtitle;
        @JsonProperty("introducao")
        private final String summary;
        private final String link;
        @JsonProperty("imagens")
        private String imagem;

        public NewsItem(String title, String subtitle, String summary, String link, String imagem) {
            this.title = title;
            this.subtitle = subtitle;
            this.summary = summary;
            this.link = link;
            this.imagem = imagem;
        }
    }
}
