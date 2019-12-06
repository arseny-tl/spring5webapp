package com.tl.spring5webapp.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@Getter
@Setter
public class Author extends BaseEntity {

  private String firstName;
  private String lastName;

  @ManyToMany(mappedBy = "authors")
  private Set<Book> books = new HashSet<>();

  public Author() {}

  public Author(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public void addBook(Book book) {
    if (this.books == null) {
      this.books = new HashSet<>();
    }
    this.books.add(book);
  }

  public void removeBook(Book book) {
    if (this.books == null) {
      this.books = new HashSet<>();
    }
    this.books.remove(book);
  }
}
