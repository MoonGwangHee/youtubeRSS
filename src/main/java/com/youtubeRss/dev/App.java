package com.youtubeRss.dev;

import com.youtubeRss.dev.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println(">>> RSS 수집기 서버 가동 중... (종료하려면 Ctrl+C)");


    }
}
