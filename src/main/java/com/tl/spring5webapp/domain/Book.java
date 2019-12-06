package com.tl.spring5webapp.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Book extends BaseEntity {

  private String title;
  private String isbn;

  @OneToOne private Publisher publisher;

  @ManyToMany
  @JoinTable(
      name = "author_book",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "author_id"))
  private Set<Author> authors = new HashSet<>();

  public Book() {}

  public Book(String title, String isbn, Publisher publisher) {
    this.title = title;
    this.isbn = isbn;
    this.publisher = publisher;
  }

  public void addAuthor(Author author) {
    if (this.authors == null) {
      this.authors = new HashSet<>();
    }
    this.authors.add(author);
  }

  public void removeAuthor(Author author) {
    if (this.authors == null) {
      this.authors = new HashSet<>();
    }
    this.authors.remove(author);
  }
}
