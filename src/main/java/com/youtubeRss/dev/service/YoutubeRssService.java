package com.youtubeRss.dev.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.youtubeRss.dev.model.YoutubeItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class YoutubeRssService {

    @Value("${youtube.channel.id}")
    private String myFavoriteChannel;

    public List<YoutubeItem> fetchRss(String channelId) {
        List<YoutubeItem> items = new ArrayList<>();
        // 유튜브 RSS 공식 주소 패턴
        String url = "https://www.youtube.com/feeds/videos.xml?channel_id=" + channelId;

        try {
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(new URL(url)));

            for (SyndEntry entry : feed.getEntries()) {
                YoutubeItem item = new YoutubeItem();
                item.setTitle(entry.getTitle());
                item.setLink(entry.getLink());
                item.setAuthor(entry.getAuthor());

                // 유튜브 비디오 ID 추출
                String link = entry.getLink();
                item.setVideoId(link.substring(link.lastIndexOf("/") + 1));

                items.add(item);
            }
        } catch (Exception e) {
            System.err.println("RSS 수집 실패: " + e.getMessage());
        }
        return items;
    }

    @Scheduled(fixedRate = 600000)
    public void collectAndNotify() {
        List<YoutubeItem> items = fetchRss(myFavoriteChannel);

        // DB를 확인 후, 이미 보낸 videoId 인지, 체크하는 로직
        // 지금은 콘솔에 하고, 다음에는 디코 웹훅으로 쏜다.
        for (YoutubeItem item : items) {
            System.out.println("신규 영상 발견!! 보낼 준비 중: " + item.getTitle());
        }
    }

}
