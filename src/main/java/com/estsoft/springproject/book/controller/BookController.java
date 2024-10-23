package com.estsoft.springproject.book.controller;

import com.estsoft.springproject.book.domain.Book;
import com.estsoft.springproject.book.domain.BookDTO;
import com.estsoft.springproject.book.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;

    }

    // 책 전체 조회
    // GET  /books
    @GetMapping
    public String showAll(Model model) {
        List<BookDTO> list = bookService.findAll()
                .stream()
                .map(BookDTO::new)
                .toList();
        model.addAttribute("bookList", list);
        return "bookManagement";
    }

    // 책 단권 조회
    @GetMapping("/{id}")
    public String showOne(@PathVariable String id, Model model) {
        Book book = bookService.findBy(id);
        model.addAttribute("book", new BookDTO(book));
        return "bookDetail";
    }


    // 책 생성
    // POST /books
    // id, name author 정보 받아서 DB에 저장
    // 저장된 책 정보가 바로 노출될 수 있도록 화면 구성
    @PostMapping
    public String addBook(@RequestParam String id,
                          @RequestParam String name,
                          @RequestParam String author) {
        bookService.saveOne(new Book(id,name,author));

        return "redirect:/books";  // GET /books  3xx  다른 url get 메소드로 연결해준다는 뜻
        // books라는 GET 메소드를 리다이렉션
    }
}