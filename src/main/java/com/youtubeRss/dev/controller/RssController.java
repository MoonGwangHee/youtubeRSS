package com.youtubeRss.dev.controller;

import com.youtubeRss.dev.model.YoutubeItem;
import com.youtubeRss.dev.service.YoutubeRssService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rss")
public class RssController {

    private final YoutubeRssService youtubeRssService;

    public RssController(YoutubeRssService youtubeRssService) {
        this.youtubeRssService = youtubeRssService;
    }

    @GetMapping("/collect")
    public List<YoutubeItem> collectByUrl(@RequestParam String url) {
        String channelId = youtubeRssService.getChannelIdFromUrl(url);
        return youtubeRssService.fetchRss(channelId);
    }
}
