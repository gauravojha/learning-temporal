package dev.gauravojha.learningtemporal;

import io.temporal.common.converter.DataConverter;
import io.temporal.common.converter.DefaultDataConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LearningTemporalApplication {

  @Bean
  public DataConverter dataConverter() {
    return DefaultDataConverter.newDefaultInstance();
  }

  public static void main(String[] args) {
    SpringApplication.run(LearningTemporalApplication.class, args).start();
  }
}
