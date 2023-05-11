package cse.cs307.databaseproj2.config;

import cse.cs307.databaseproj2.Interceptor.UserAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UserAccessConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserAccessInterceptor()).addPathPatterns("/user/**");
    }
}
