package com.practice.bookcurd.controller;

import com.practice.bookcurd.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    Book book;

    /* controller for
    1) Post/books           create new book
    2) Get/books            retrieve a list of all books.
    3) Get/books/{id}       retrieve a book bu it's ID
    4) Put/books/{id}       update book details title and author should not empty
    5) Delete/books/{id}    delete book by ID
     */
}
