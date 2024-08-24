package com.practice.bookcurd.service;

import com.practice.bookcurd.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IBookService {


    Book createAndSaveBook(Book book);
    Book getBookById(Long id);
    List<Book> getAllBooks();
    Book updateBookById(Long id, Book book);
    String DeleteBookById(Long bookId);
    List<Book> getBooksByCategory(String categotyName);
    List<Book> getBooksByCategoryUsingRequestParam(String categotyName);
    Page<Book> getAllBooksByPagination(int page, int size);
}
