package com.practice.bookcurd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @NotBlank
    private String bookName;
    @NotNull
    @NotBlank
    private String author;
    @Size(min=4, max=4)
    private Integer publishedYear;

}
