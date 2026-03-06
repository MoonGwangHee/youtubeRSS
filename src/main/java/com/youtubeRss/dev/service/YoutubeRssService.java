package com.youtubeRss.dev.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class YoutubeRssService {
    //  5초를 기준으로 로그 생성
    @Scheduled(fixedRate = 5000)
    public void collect() {
        System.out.println("RSS 수집 중... (5초마다 실행)");

    }
}
