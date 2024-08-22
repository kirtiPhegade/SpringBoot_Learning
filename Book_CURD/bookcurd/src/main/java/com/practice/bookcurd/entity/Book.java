package com.practice.bookcurd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @NotNull(message = "Book Name cannot be empty")
    @NotBlank(message = "Book Name cannot be blank")
    private String bookName;
    @NotNull(message = "Book Author Name cannot be empty")
    @NotBlank(message = "Book Author Name cannot be blank")
    private String author;
    //@Size(min=4, max=4) can not use with Integer only use with string, collection, map, array
    /* error
     No validator could be found for constraint 'jakarta.validation.constraints.Size' validating type
     'java.lang.Integer'. Check configuration for 'publishedYear'] with root cause
     */
    @Min(value = 1000, message = "Published year cannot be earlier than 1000")
    @Max(value = 2090, message = "Published year cannot be later than 2090")
    private Integer publishedYear;

}
