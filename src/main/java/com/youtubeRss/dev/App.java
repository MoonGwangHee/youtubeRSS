package com.youtubeRss.dev;

import com.youtubeRss.dev.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println(">>> 스프링 컨테이너 구동 완료!!!");
    }
}
