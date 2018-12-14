package com.insat.serviceImpl;

import com.insat.model.Book;
import com.insat.repository.BookRepository;
import com.insat.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;


    public List<Book> getAll(String sortby) {
        return bookRepository.findAll(Sort.by(sortby));
    }


    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }


    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }


    public Book addOne(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findBooksByTitle(title);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthor( author);
    }

    @Override
    public List<Book> getBooksByTag(String tag) {
        return bookRepository.findBooksByTag(tag);
    }
}
