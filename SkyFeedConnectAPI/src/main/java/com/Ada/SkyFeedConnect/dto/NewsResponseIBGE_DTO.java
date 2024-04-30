package com.Ada.SkyFeedConnect.dto;

import com.Ada.SkyFeedConnect.model.NewsResponseIBGE;

import java.util.List;

/**
 * DTO class representing news response data from IBGE.
 */

public record NewsResponseIBGE_DTO(List<NewsResponseIBGE.NewsItem> newsList) {
}
