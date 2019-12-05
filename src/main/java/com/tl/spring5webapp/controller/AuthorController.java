package com.tl.spring5webapp.controller;

import com.tl.spring5webapp.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class AuthorController {

  private final AuthorRepository authorRepository;

  @RequestMapping("/author")
  public String getAllAuthors(Model model) {
    model.addAttribute("authors", authorRepository.findAll());
    return "authors";
  }
}
