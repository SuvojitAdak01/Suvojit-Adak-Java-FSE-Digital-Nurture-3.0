package com.example.bookstore.controller;

import com.example.bookstore.metrics.BookstoreMetrics;
import com.example.bookstore.model.Book;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookstoreMetrics bookstoreMetrics;

    public BookController(BookstoreMetrics bookstoreMetrics) {
        this.bookstoreMetrics = bookstoreMetrics;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Book getBook(@RequestParam(value = "title", defaultValue = "Sample Book") String title) {
        bookstoreMetrics.incrementBooksSold(); // Increment the custom metric
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor("John Doe");
        book.setIsbn("123-4567891234");
        return book;
    }
}