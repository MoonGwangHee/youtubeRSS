package com.youtubeRss.dev.service;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.youtubeRss.dev.model.YoutubeItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class YoutubeRssService {

    @Value("${youtube.channel.id}")
    private String myFavoriteChannel;

    public List<YoutubeItem> fetchRss(String channelId) {

        if (channelId == null || channelId.isEmpty()) {
            return new ArrayList<>();
        }

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
            System.out.println(">>> [" + channelId + "] 데이터 수집 완료!");
        } catch (Exception e) {
            System.err.println("RSS 수집 실패: " + e.getMessage());
        }
        return items;
    }

    public void collectAndNotify() {
        List<YoutubeItem> items = fetchRss(myFavoriteChannel);

        // DB를 확인 후, 이미 보낸 videoId 인지, 체크하는 로직
        // 지금은 콘솔에 하고, 다음에는 디코 웹훅으로 쏜다.
        for (YoutubeItem item : items) {
            System.out.println("신규 영상 발견!! 보낼 준비 중: " + item.getTitle());
        }
    }

    public String getChannelIdFromUrl(String youtubeHomeUrl) {
        return extractBroseId(youtubeHomeUrl);
    }

    public String extractBroseId(String handleUrl) {
        try {
            Document doc = Jsoup.connect(handleUrl)
                    .timeout(5000)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36")
                    .get();
            String html = doc.html();
            Pattern pattern = Pattern.compile("\"browseId\":\"(UC[^\"]+)\"");
            Matcher matcher = pattern.matcher(html);
            if (matcher.find()) {
                String foundId = matcher.group(1);
                System.out.println(">>> 추출된 ID: " + foundId);
            }
        } catch (IOException e) {
            System.err.println("URL 접속 실패: " + e.getMessage());
        }
        return null;
    }


}
