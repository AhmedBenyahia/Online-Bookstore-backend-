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
    public ResponseEntity<List> getAllBooksSorted(@PathVariable String sortby,
                            @RequestHeader("Authorization") String token)
            throws UnsupportedEncodingException {
        System.out.println("get all books"+token);
        if(authService.isValid(token)) {
                if (sortby.toLowerCase().equals("author")) sortby = "author";
            else if (sortby.toLowerCase().equals("title")) sortby = "title";
            else if (sortby.toLowerCase().equals("price")) sortby = "price";
                else sortby = "date";
            return new ResponseEntity<>(bookService.getAll(sortby), HttpStatus.OK);
        } else
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = {"/books/title/{title}"})
    public ResponseEntity<List> getBooksByTitle(
            @PathVariable(value = "title") String title,
            @RequestHeader("Authorization") String token) throws UnsupportedEncodingException {
        System.out.println("get all books"+token);
        if(authService.isValid(token)) {
            return new ResponseEntity<>(bookService.getBooksByTitle(title),
                    HttpStatus.OK);
        } else  return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = {"/books/author/{author}"})
    public ResponseEntity<List> getBooksByAuthor(@PathVariable(value = "author") String author,
                                 @RequestHeader("Authorization") String token)
            throws UnsupportedEncodingException {
        System.out.println("get books by author"+token);
        if(authService.isValid(token)) {
            return new ResponseEntity<>(bookService.getBooksByAuthor(author),
                    HttpStatus.OK);
        }  else  return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = {"/books/tag/{tag}"})
    public ResponseEntity<List> getBooksByTag(
            @PathVariable(value = "tag") String tag,
            @RequestHeader("Authorization") String token)
            throws UnsupportedEncodingException {
        System.out.println("get books by tag"+token);
        if(authService.isValid(token)) {
            return new ResponseEntity<>(bookService.getBooksByTag(tag),
                    HttpStatus.OK);
        }  else  return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/books/id/{id}")
    public ResponseEntity<Optional<Book>> getBookbyId(@PathVariable("id") Long id,
                                                      @RequestHeader("Authorization") String token)
            throws UnsupportedEncodingException {
        System.out.println("get books by id "+token);
        if(authService.isValid(token)) {
            return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
        }  else  return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/books/add")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book,
                        @RequestHeader("Authorization") String token)
            throws UnsupportedEncodingException {
        System.out.println("add a book "+token);
        if(authService.isValid(token)) {
            return new ResponseEntity<>(bookService.addOne(book),HttpStatus.OK);
        } else  return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/books/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable ("id") Long id,
                           @RequestHeader("Authorization") String token)
            throws UnsupportedEncodingException {
        System.out.println("delete  book "+token);
        if(authService.isValid(token)) {
            bookService.deleteById(id);
           return new ResponseEntity<>(HttpStatus.OK);
        } else  return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/books/update")
    public ResponseEntity<Book> updateBook(
            @RequestBody Book newBook,
            @RequestHeader("Authorization") String token)
                 throws UnsupportedEncodingException {
        System.out.println("update book \n -> " + token);
        if (authService.isValid(token)) {
            Optional<Book> oldBook = getBookbyId(newBook.getId(),token).getBody();
            if (oldBook.isPresent()) {
                return new ResponseEntity<Book>(addBook(newBook,token).getBody(), HttpStatus.OK);
            } else {
                return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
            }
        } return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
