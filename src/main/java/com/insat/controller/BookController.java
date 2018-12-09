package com.insat.controller;


import com.insat.model.Book;
import com.insat.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@EnableAutoConfiguration
public class BookController {

    @Autowired
    BookService bookService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/books/{sortby}")
    public List getAllBooks(@PathVariable String sortby) {
        if (sortby.toLowerCase().equals("author"))sortby = "Author";
        else sortby = "Title";
        return bookService.getAll(sortby);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = {"/books/title/{title}"})
    public List getBooksByTitle(@PathVariable(value = "title") String title) {
        return bookService.getBooksByTitle(title);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = {"/books/author/{author}"})
    public List getBooksByAuthor(@PathVariable(value = "author") String author) {
        return bookService.getBooksByAuthor(author);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/books/id/{id}")
    public Optional<Book> getBookbyId(@PathVariable("id") Long id){
        return bookService.getById(id);
    }

//    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/books/add")
    public Book addBook(@Valid @RequestBody Book book){
        return bookService.addOne(book);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/books/delete/{id}")
    public void deleteBook(@PathVariable ("id") Long id){
         bookService.deleteById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/books/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book newBook){
        Optional<Book> oldBook = getBookbyId(newBook.getId());
        if(oldBook.isPresent()){
            return new ResponseEntity<Book>(addBook(newBook),HttpStatus.OK);
        }else {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }

}
