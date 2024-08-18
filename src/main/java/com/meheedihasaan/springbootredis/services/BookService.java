package com.meheedihasaan.springbootredis.services;

import com.meheedihasaan.springbootredis.entities.Book;
import com.meheedihasaan.springbootredis.models.requests.CreateBookRequest;
import com.meheedihasaan.springbootredis.models.requests.UpdateBookRequest;
import com.meheedihasaan.springbootredis.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Cacheable(cacheNames = "books")
    public List<Book> getAll() {
        log.info("Getting all books from DB");
        return bookRepository.findAll();
    }

    @Cacheable(cacheNames = "book", key = "#id", unless = "#result==null")
    public Book getOne(UUID id) {
        log.info("Getting single book from DB");
        return bookRepository.findById(id).orElse(null);
    }

    @CacheEvict(cacheNames = "books", allEntries = true)
    public UUID create(CreateBookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book = bookRepository.save(book);
        return book.getId();
    }

    @CachePut(cacheNames = "book", key = "#id")
    @CacheEvict(cacheNames = "books", allEntries = true)
    public Book update(UUID id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            log.error("Book with id: {} not found", id);
            return null;
        }

        log.info("Updating single book");

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        return bookRepository.save(book);
    }

    @Caching(evict = {@CacheEvict(cacheNames = "books", allEntries = true), @CacheEvict(cacheNames = "book", key = "#id")})
    public void delete(UUID id) {
        bookRepository.deleteById(id);
    }
}
