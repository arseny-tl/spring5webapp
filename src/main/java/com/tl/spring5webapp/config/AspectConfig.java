package com.tl.spring5webapp.config;

import com.tl.aspect.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {
  @Bean
  LoggingAspect loggingAscept() {
    return new LoggingAspect();
  }
}
