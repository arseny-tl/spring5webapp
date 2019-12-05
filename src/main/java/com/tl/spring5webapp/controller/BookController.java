package com.tl.spring5webapp.controller;

import com.tl.spring5webapp.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class BookController {

  private final BookRepository bookRepository;

  @RequestMapping("/books")
  public String getBooks(Model model) {
    model.addAttribute("books", bookRepository.findAll());
    return "books";
  }
}
