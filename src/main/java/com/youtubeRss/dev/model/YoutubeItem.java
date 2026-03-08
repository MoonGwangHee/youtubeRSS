package com.youtubeRss.dev.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class YoutubeItem {

    private String title;   // 영상 제목
    private String link;    // 영상 URL
    private String videoId; // 유튜브 고유 ID
    private String author;  // 유튜브 이름
    private LocalDateTime pubDate;  // 업로드 시간
}
