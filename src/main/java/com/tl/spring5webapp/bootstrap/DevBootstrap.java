package com.tl.spring5webapp.bootstrap;

import com.tl.spring5webapp.model.Author;
import com.tl.spring5webapp.model.Book;
import com.tl.spring5webapp.model.Publisher;
import com.tl.spring5webapp.model.User;
import com.tl.spring5webapp.model.UserRole;
import com.tl.spring5webapp.repository.AuthorRepository;
import com.tl.spring5webapp.repository.BookRepository;
import com.tl.spring5webapp.repository.PublisherRepository;
import com.tl.spring5webapp.repository.UserRepository;
import com.tl.spring5webapp.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;
  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    initData();
    initUsers();
  }

  private void initData() {
    Author eric = new Author("Eric", "Evans");
    Publisher harper = new Publisher("Harper Collins", "");
    Book ddd = new Book("Domain driven design", "1234", harper);
    eric.getBooks().add(ddd);
    ddd.getAuthors().add(eric);

    publisherRepository.save(harper);
    authorRepository.save(eric);
    bookRepository.save(ddd);

    Author rod = new Author("Rod", "Johnson");
    Publisher worx = new Publisher("Workx", "");
    Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
    rod.getBooks().add(noEJB);
    noEJB.getAuthors().add(rod);

    publisherRepository.save(worx);
    authorRepository.save(rod);
    bookRepository.save(noEJB);
  }

  private void initUsers() {
    UserRole guestRole = new UserRole();
    guestRole.setName("USER");

    User guest = new User();
    guest.setPassword("asd");
    guest.setUsername("guest");
    guest.addRole(guestRole);
    guestRole.getUsers().add(guest);

    userRepository.save(guest);
    userRoleRepository.save(guestRole);
  }
}
