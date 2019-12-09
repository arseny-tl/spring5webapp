package com.tl.spring5webapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {

  @GetMapping
  public String asd() {
    return "Hello World!";
  }
}
