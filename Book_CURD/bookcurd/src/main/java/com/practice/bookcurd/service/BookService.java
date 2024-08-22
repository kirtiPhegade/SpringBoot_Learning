package com.practice.bookcurd.service;

import com.practice.bookcurd.entity.Book;
import com.practice.bookcurd.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    Book book;

    @Override
    public Book createAndSaveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
