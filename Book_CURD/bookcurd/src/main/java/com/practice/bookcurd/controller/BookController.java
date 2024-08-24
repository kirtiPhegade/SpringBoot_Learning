package com.practice.bookcurd.controller;

import com.practice.bookcurd.entity.Book;
import com.practice.bookcurd.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    Book book;
    @Autowired
    BookService bookService;
    @Autowired
    private View error;

    /* controller for
    1) Post/books                   create new book
    2) Get/books                    retrieve a list of all books.
    3) Get/books/{id}               retrieve a book bu it's ID
    4) Put/books/{id}               update book details title and author should not empty
    5) Delete/books/{id}            delete book by ID
    6) add new categotyName in our table
    7) Get/books/{categotyName}     search book by category name
    8) Get/books/search             search book use @RequestParam.
                                    The search should be case-insensitive and should allow partial matches
                                    (e.g., searching for "Java" should return books like "Effective Java" and "Java Concurrency in Practice").
    9) Get/books/paginated          get all book use pagination use @RequestParam
     */

    /*Create new Book and save in database
    1)use @PostMapping("/books")
    2)use jpa save()
    3)201 status code when book is created else 400 bad request
    */

    @PostMapping("/books")
    public ResponseEntity<?> createAndSaveBook(@Valid @RequestBody Book book, BindingResult bindingResult){
        /*if(bindingResult.hasErrors()){
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            error -> error.getField(),
                            error -> error.getDefaultMessage(),
                    ));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        Duplicate key author (attempted merging values Book Author Name cannot be blank and Book Author Name cannot be empty)] with root cause
        This error occurs when the stream tries to merge two entries with the same key but different values
        ****************************************************
        To resolve this, you need to decide how to handle the duplicate keys. If you want to keep one of the values, you can specify a merge function to resolve conflicts when keys are duplicated.

        Here’s how you can modify the stream to handle this situation:

        java
        Copy code
        Map<String, String> validationMessages = yourStream.collect(
            Collectors.toMap(
                keyMapper,
                valueMapper,
                (existingValue, newValue) -> existingValue // or newValue, depending on which you want to keep
            )
        );
        In this example:

        keyMapper is the function that extracts the key.
        valueMapper is the function that extracts the value.
        The third parameter, (existingValue, newValue) -> existingValue, is the merge function.
         It specifies that in case of a conflict (duplicate keys), the existing value should be kept.
         If you want to use the new value instead, you can replace existingValue with newValue.
                 */
        if(bindingResult.hasErrors()){
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            error -> error.getField(),
                            error -> error.getDefaultMessage(),
                            (existing, replacement) -> existing
                    ));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
        Book savedBook = bookService.createAndSaveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable("bookId") Long bookId){
        book = bookService.getBookById(bookId);
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book is not found. please check book id.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        if(books.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book is not found.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(books);
        }
    }

    @PutMapping("/book/{bookId}")
    public ResponseEntity<?> UpdateBookById(@PathVariable Long bookId, @Valid @RequestBody Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String,String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            error -> error.getField(),
                            error -> error.getDefaultMessage(),
                            (e,r) -> e

                    ));
        }
        book = bookService.updateBookById(bookId,book);
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book is not found.");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(book);
        }
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<?> DeleteBookById(@PathVariable Long bookId){
        String isDeleted = bookService.DeleteBookById(bookId);
        if(isDeleted == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(isDeleted);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
        }
    }

    @GetMapping("/books/{categotyName}")
    public ResponseEntity<?> getBooksByCategory(@PathVariable("categotyName") String categotyName){
        List<Book> books = bookService.getBooksByCategory(categotyName);
        if(books.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book is not found.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(books);
        }
    }

    @GetMapping("/books/search")
    public ResponseEntity<?> getBooksByCategoryUsingRequestParam(@RequestParam(value = "categotyName", required = false) String categotyName){
        List<Book> books = bookService.getBooksByCategoryUsingRequestParam(categotyName);
        if(books.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book is not found.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(books);
        }
    }

    @GetMapping("/books/paginated")
    public ResponseEntity<?> getAllBooksByPagination(@RequestParam(defaultValue = "0",required = false) int page, @RequestParam(defaultValue = "3",required = false) int size){
        Page<Book> books = bookService.getAllBooksByPagination(page, size);
        if(books.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book is not found.");
        }else {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }
}
