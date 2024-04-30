package com.Ada.SkyFeedConnect.controller;

import com.Ada.SkyFeedConnect.dto.NewsResponseIBGE_DTO;
import com.Ada.SkyFeedConnect.service.NewsResponseIBGE_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/newsIBGE")
@CrossOrigin
public class NewsIBGEController {

  private final NewsResponseIBGE_Service newsIBGEService;

  @Autowired
  public NewsIBGEController(NewsResponseIBGE_Service newsIBGEService) {
    this.newsIBGEService = newsIBGEService;
  }


    /**
     * Retrieves news from IBGE based on the specified quantity.
     *
     * @param qtd The quantity of news items to retrieve.
     * @return A DTO containing news data from IBGE.
     */

  @GetMapping("/{qtd}")
  public NewsResponseIBGE_DTO getNewsIBGE(@PathVariable Integer qtd) {
    return newsIBGEService.getNewsIBGE(qtd);
  }
}
