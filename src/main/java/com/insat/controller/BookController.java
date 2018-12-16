package com.insat.controller;


import com.insat.model.Book;
import com.insat.service.AuthService;
import com.insat.service.BookService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@EnableAutoConfiguration
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthService authService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/books/{sortby}")
    public ResponseEntity<List> getAllBooksSorted(@PathVariable String sortby){
        System.out.println("get all books \n");

        switch (sortby.toLowerCase()) {
            case "author":
                sortby = "author";
                break;
            case "title":
                sortby = "title";
                break;
            case "price":
                sortby = "price";
                break;
            default:
                sortby = "date";
                break;
        }
        return new ResponseEntity<>(bookService.getAll(sortby), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = {"/books/title/{title}"})
    public ResponseEntity<List> getBooksByTitle( @PathVariable(value = "title") String title) {

        System.out.println("get book with title\n");
            return new ResponseEntity<>(
                    bookService.getBooksByTitle(title),
                    HttpStatus.OK
            );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = {"/books/author/{author}"})
    public ResponseEntity<List> getBooksByAuthor(@PathVariable(value = "author") String author) {

            System.out.println("get books by author \n");
            return new ResponseEntity<>(bookService.getBooksByAuthor(author),
                    HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = {"/books/tag/{tag}"})
    public ResponseEntity<List> getBooksByTag( @PathVariable(value = "tag") String tag) {

            System.out.println("get books by tag \n");
            return new ResponseEntity<>(bookService.getBooksByTag(tag),
                    HttpStatus.OK);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/books/id/{id}")
    public ResponseEntity<Optional<Book>> getBookbyId(@PathVariable("id") Long id) {

            System.out.println("get books by id \n");
            return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/books/add")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {

            System.out.println("add a book \n");
            return new ResponseEntity<>(bookService.addOne(book),HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/books/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable ("id") Long id) {

            System.out.println("delete  book \n");
            bookService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/books/update")
    public ResponseEntity<Book> updateBook( @RequestBody Book newBook)  {
            System.out.println("update book \n");
            Optional<Book> oldBook = getBookbyId(newBook.getId()).getBody();
            if (oldBook.isPresent()) {
                return new ResponseEntity<>(addBook(newBook).getBody(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

}
