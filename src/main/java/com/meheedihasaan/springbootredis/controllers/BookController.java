package com.meheedihasaan.springbootredis.controllers;

import com.meheedihasaan.springbootredis.entities.Book;
import com.meheedihasaan.springbootredis.models.requests.CreateBookRequest;
import com.meheedihasaan.springbootredis.models.requests.UpdateBookRequest;
import com.meheedihasaan.springbootredis.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> getOne(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(bookService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateBookRequest request) {
        UUID id = bookService.create(request);
        URI location = ServletUriComponentsBuilder.fromPath("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @Valid @RequestBody UpdateBookRequest request) {
        bookService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
