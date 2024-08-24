package com.practice.bookcurd.repository;

import com.practice.bookcurd.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    /*
    this method is not work when our input is java this is only work
    if our input is java program
     */
    List<Book> findAllByCategotyName(String categotyName);
    /*
    Containing: This keyword allows partial matches in your queries.
    IgnoreCase: This makes the search case-insensitive.
     */
    List<Book> findAllByCategotyNameContainingIgnoreCase(String categotyName);

}
