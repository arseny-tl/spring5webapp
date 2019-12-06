package com.tl.spring5webapp.bootstrap;

import com.tl.spring5webapp.domain.Author;
import com.tl.spring5webapp.domain.Book;
import com.tl.spring5webapp.domain.Publisher;
import com.tl.spring5webapp.domain.User;
import com.tl.spring5webapp.domain.UserRole;
import com.tl.spring5webapp.repository.AuthorRepository;
import com.tl.spring5webapp.repository.BookRepository;
import com.tl.spring5webapp.repository.PublisherRepository;
import com.tl.spring5webapp.repository.UserRepository;
import com.tl.spring5webapp.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    eric.addBook(ddd);
    ddd.addAuthor(eric);

    publisherRepository.save(harper);
    authorRepository.save(eric);
    bookRepository.save(ddd);

    Author rod = new Author("Rod", "Johnson");
    Publisher worx = new Publisher("Workx", "");
    Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
    rod.addBook(noEJB);
    noEJB.addAuthor(rod);

    publisherRepository.save(worx);
    authorRepository.save(rod);
    bookRepository.save(noEJB);
  }

  private void initUsers() {
    UserRole userRole = new UserRole("USER");
    UserRole adminRole = new UserRole("ADMIN");

    User user = new User("user", new BCryptPasswordEncoder().encode("user"));
    user.addRole(userRole);
    userRole.addUser(user);

    User admin = new User("admin", new BCryptPasswordEncoder().encode("admin"));
    admin.addRole(adminRole);
    adminRole.addUser(admin);

    userRepository.save(user);
    userRoleRepository.save(userRole);
    userRepository.save(admin);
    userRoleRepository.save(adminRole);
  }
}
