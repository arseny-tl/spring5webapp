package com.tl.spring5webapp.controller;

import com.tl.spring5webapp.domain.User;
import com.tl.spring5webapp.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BookController {

  private final BookRepository bookRepository;

  @RequestMapping("/books")
  public String getBooks(Model model) {
    log.info(
        "{} requested books",
        ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
            .getUsername());
    model.addAttribute("books", bookRepository.findAll());
    return "books";
  }
}
