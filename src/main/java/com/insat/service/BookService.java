package com.insat.service;

import com.insat.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAll(String sortby);

    Optional<Book> getById(Long id);

    void deleteById(Long id);

    Book addOne(Book book);


    List<Book> getBooksByTitle(String title);
    List<Book> getBooksByAuthor(String author);

}
