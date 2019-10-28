package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;
  private final PublisherRepository publisherRepository;

  public DevBootstrap(
      AuthorRepository authorRepository,
      BookRepository bookRepository,
      PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    initData();
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
}
