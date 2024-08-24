package com.practice.bookcurd.service;

import com.practice.bookcurd.entity.Book;
import com.practice.bookcurd.repository.IBookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookRepository bookRepository;

    @Override
    public @Valid Book createAndSaveBook(Book book) {
        return bookRepository.save(book);
    }

    public @Valid Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public @Valid Book updateBookById(Long bookId, @Valid Book book) {
        Book bookWeGet = getBookById(bookId);
        if (bookWeGet != null) {
            book.setBookId(bookId);
            return bookRepository.save(book);
        }else{
            return null;
        }
    }


    public String DeleteBookById(Long bookId) {
        if(bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
            return "Book Deleted";
        }else{
            return "Book Not Found";
        }
    }

    public List<Book> getBooksByCategory(String categotyName) {
        List<Book> books = bookRepository.findAllByCategotyName(categotyName);
        if(books.isEmpty()){
            return null;
        }else{
            return books;
        }
    }

    public List<Book> getBooksByCategoryUsingRequestParam(String categotyName) {
        List<Book> books = bookRepository.findAllByCategotyNameContainingIgnoreCase(categotyName);
        if(books.isEmpty()){
            return null;
        }else{
            return books;
        }
    }

    public Page<Book> getAllBooksByPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepository.findAll(pageable);
    }
}
