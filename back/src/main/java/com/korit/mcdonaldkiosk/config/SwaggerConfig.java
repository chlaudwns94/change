package com.korit.mcdonaldkiosk.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    /**
     * Swagger(OpenAPI) 설정을 위한 빈을 등록합니다.
     * Swagger는 API 문서를 자동으로 생성하고 UI를 통해 API 테스트를 가능하게 합니다.
     *
     * @return OpenAPI Swagger 설정 객체
     */
    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI();
    }

}
