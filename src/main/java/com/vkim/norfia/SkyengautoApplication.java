package com.vkim.norfia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class SkyengautoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SkyengautoApplication.class, args);
  }

  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setForceEncoding(true);
    characterEncodingFilter.setEncoding("UTF-8");
    registrationBean.setFilter(characterEncodingFilter);
    return registrationBean;
  }

}
