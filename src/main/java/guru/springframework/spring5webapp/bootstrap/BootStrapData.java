package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner
{
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository)
    {
        // comment
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        // Initialise some Publisher data
        Publisher tor = new Publisher("Tor", "1 La Rue Jaune", "Leeds", "LE12BP");
        Publisher zap = new Publisher("Zap", "99 Big Street", "Bristol", "BA19ER");

        publisherRepository.save(tor);
        publisherRepository.save(zap);

        // Initialise some book and author data
        Author peterWatts = new Author("Peter", "Watts");
        Book blindSight  = new Book("Blindsight", "ABC123");

        Author terryPratchett = new Author("Terry", "Pratchett");
        Book hogsWatch  = new Book("Hogswatch", "DEF456");

        // Set up the m:n relationships between them
        peterWatts.getBooks().add(blindSight);
        blindSight.getAuthors().add(peterWatts);
        blindSight.setPublisher(tor);
        tor.getBooks().add(blindSight);

        // Save the data to the respective repositories
        authorRepository.save(peterWatts);
        bookRepository.save(blindSight);
        publisherRepository.save(tor);

        terryPratchett.getBooks().add(hogsWatch);
        hogsWatch.getAuthors().add(terryPratchett);
        hogsWatch.setPublisher(zap);
        zap.getBooks().add(hogsWatch);

        // Save the data to the respective repositories
        authorRepository.save(terryPratchett);
        bookRepository.save(hogsWatch);
        publisherRepository.save(zap);

        System.out.println("Ran bootstrap data...");
        System.out.println("Num Books: " + bookRepository.count());
        System.out.println("Num Authors: " + authorRepository.count());
        System.out.println("Num Publishers: " + publisherRepository.count());
        System.out.println("Publisher: " + tor.getName() + ", num books: " + tor.getBooks().size());
        System.out.println("Publisher: " + zap.getName() + ", num books: " + zap.getBooks().size());
    }
}
