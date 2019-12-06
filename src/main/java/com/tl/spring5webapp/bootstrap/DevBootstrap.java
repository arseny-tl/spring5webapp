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
    UserRole userRole = new UserRole();
    userRole.setName("USER");
    UserRole adminRole = new UserRole();
    adminRole.setName("ADMIN");

    User user = new User();
    user.setPassword(new BCryptPasswordEncoder().encode("user"));
    user.setUsername("user");
    user.addRole(userRole);
    userRole.addUser(user);

    User admin = new User();
    admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
    admin.setUsername("admin");
    admin.addRole(adminRole);
    adminRole.addUser(admin);

    userRepository.save(user);
    userRoleRepository.save(userRole);
    userRepository.save(admin);
    userRoleRepository.save(adminRole);
  }
}
