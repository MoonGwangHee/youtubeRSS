# 📺 Youtube-RSS-to-Discord
> **"좋아하는 유튜버의 소식을 가장 빠르게!"** > Spring Framework와 Rome Library를 활용한 유튜브 채널 RSS 수집 및 디스코드 알림 자동화 시스템입니다.

---

## 🚀 프로젝트 개요
- **목적**: 유튜브 공식 RSS 피드를 활용하여 신규 영상 업로드 시 디스코드 웹훅으로 실시간 알림을 전송합니다.
- **주요 특징**:
  - **정통 Spring Framework** 기반의 Bean 관리 및 스케줄링 구현.
  - **Rome Library**를 이용한 XML RSS 피드 파싱.
  - **Discord Webhook** 연동을 통한 실시간 푸시 알림.
  - **Duplicate Check**: DB(H2) 연동을 통한 중복 알림 방지 (예정).

## 🛠 Tech Stack
- **Language**: Java 17 (or 21)
- **Framework**: Spring Framework 6.x
- **Build Tool**: Maven
- **Library**: Rome, Lombok, Spring Web (RestTemplate)
- **Database**: H2 Database (Local)

## 🏗 Architecture

1. **Collector**: `SyndFeedInput`을 통해 유튜브 RSS 데이터 수집.
2. **Processor**: 기존 전송 데이터와 비교하여 신규 영상 여부 판별.
3. **Notifier**: Discord API를 통해 알림 전송.

## ⚙️ 실행 방법 (Usage)
1. 프로젝트 클론
   ```bash
   git clone [https://github.com/사용자이름/youtube-rss-discord.git](https://github.com/사용자이름/youtube-rss-discord.git)
