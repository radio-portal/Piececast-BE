package org.nhnacademy.piececast.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로
                .allowedOrigins(
                        "http://127.0.0.1:5173",
                        "https://piececast.netlify.app"
                )
                .allowedMethods("*") // GET, POST 등 모든 메소드 허용
                .allowedHeaders("*")
                .allowCredentials(true); // 쿠키 포함 요청 허용
    }
}
