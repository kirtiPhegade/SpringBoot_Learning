package com.practice.bookcurd.service;

import com.practice.bookcurd.entity.Book;
import com.practice.bookcurd.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    Book book;
}
