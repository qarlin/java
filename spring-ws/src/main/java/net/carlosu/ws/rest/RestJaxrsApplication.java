package net.carlosu.ws.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class RestJaxrsApplication extends SpringBootServletInitializer {
  public static void main(String... args) {
    SpringApplication.run(RestJaxrsApplication.class, args);
  }
}
