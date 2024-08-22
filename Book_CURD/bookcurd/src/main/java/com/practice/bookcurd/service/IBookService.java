package com.practice.bookcurd.service;

import com.practice.bookcurd.entity.Book;


public interface IBookService {


    Book createAndSaveBook(Book book);
    Book getBookById(Long id);
}
