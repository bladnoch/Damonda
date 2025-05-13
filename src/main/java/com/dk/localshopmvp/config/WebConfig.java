package com.dk.localshopmvp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * packageName    : com.dk.localshopmvp.config
 * fileName       : WebConfig
 * author         : doungukkim
 * date           : 2025. 5. 13.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 5. 13.        doungukkim       최초 생성
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 내가 제안한 경로: /uploads/** 요청 → 실제 ./uploads/ 폴더에서 찾도록 설정
        String uploadPath = System.getProperty("user.dir") + "/uploads/";

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadPath);
    }
}

