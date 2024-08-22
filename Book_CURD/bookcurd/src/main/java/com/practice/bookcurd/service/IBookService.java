package com.practice.bookcurd.service;

import com.practice.bookcurd.entity.Book;

import java.util.List;


public interface IBookService {


    Book createAndSaveBook(Book book);
    Book getBookById(Long id);
    List<Book> getAllBooks();
}
