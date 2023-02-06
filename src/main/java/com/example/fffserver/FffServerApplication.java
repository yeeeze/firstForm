package com.example.fffserver;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableMongoRepositories
@EnableMongoAuditing
@EnableScheduling
public class FffServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(FffServerApplication.class, args);
  }

  @PostConstruct
  public void started() {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("http://www.first-form.shop");
        registry.addMapping("/**").allowedOrigins("https://www.first-form.shop");
        registry.addMapping("/**").allowedOrigins("https://first-form.shop");
      }
    };
  }
}
