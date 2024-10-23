package com.estsoft.springproject.book.service;

import com.estsoft.springproject.book.domain.Book;
import com.estsoft.springproject.book.repository.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository){
        this.repository = repository;
    }

    public List<Book> findAll(){
        return repository.findAll(Sort.by("id"));  // 기본 오름차순
        //
    }

    public Book findBy(String id){
        return repository.findById(id).orElse(new Book());
    }
    public Book saveOne(Book book){
        return repository.save(book);
    }
}
