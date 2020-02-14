package com.tl.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Slf4j
public class LoggingAspect {

  @Before(value = "execution(* com.tl.spring5webapp.bootstrap.DevBootstrap.onApplicationEvent(..))")
  public void logDevBootstrapBefore() {
    log.info("DevBootstrap loads dev data");
  }

  @After(value = "execution(* com.tl.spring5webapp.bootstrap.DevBootstrap.onApplicationEvent(..))")
  public void logDevBootstrapAfter() {
    log.info("DevBootstrap finished loading dev data");
  }
}
