package com.estsoft.springproject.book.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Book {
    @Id
    String id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    String name;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    String author;

}
