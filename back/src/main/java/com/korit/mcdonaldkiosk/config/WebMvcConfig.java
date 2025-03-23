package com.korit.mcdonaldkiosk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${user.dir}")
    private String rootPath; // 애플리케이션의 루트 디렉터리 경로를 설정 값으로 주입

    /**
     * 정적 자원(이미지 파일)의 경로를 설정하는 메서드입니다.
     * "/image/**"로 요청이 들어오면, "file:{루트 경로}/upload" 디렉터리에서 해당 파일을 찾습니다.
     * URL 디코딩을 수행하여 올바른 파일 경로를 해석합니다.
     *
     * @param registry 정적 리소스 핸들러를 관리하는 객체
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:" + rootPath + "/upload") // 로컬 업로드 폴더와 매핑
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        resourcePath = URLDecoder.decode(resourcePath, StandardCharsets.UTF_8); // URL 디코딩 처리
                        return super.getResource(resourcePath, location);
                    }
                });
    }
}
