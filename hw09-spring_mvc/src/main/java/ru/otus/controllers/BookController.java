package ru.otus.controllers;

import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.entities.Book;
import ru.otus.exceptions.NotFoundException;
import ru.otus.exceptions.ValidateException;

/*
 * @created 06/12 - otus-spring
 * @author Ilya Rogatkin
 */
public interface BookController {
    public String viewBooks(Model model);
    public String addBookPage(Model model);
    public RedirectView addBook(Book book) throws ValidateException;
    public String editBookPage(long id, Model model) throws ValidateException, NotFoundException;
    public RedirectView editBook(Book book) throws ValidateException, NotFoundException;
    public RedirectView deleteBook(Book book) throws ValidateException, NotFoundException;
}
