package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.domains.Book;
import ru.otus.services.BookService;


/**
 * Created by ilya on Oct, 2020
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
       ConfigurableApplicationContext ctx =  SpringApplication.run(Application.class);
       BookService bookService = ctx.getBean(BookService.class);
       Book book = bookService.findById(1);
        System.out.println(book.getName());
       //       Book book = new Book("Algebra");
//       bookService.save(book);
//       for(Book book1 : books) {
//           System.out.println(book1.getName());
//       }
    }
}
