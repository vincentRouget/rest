package com.wildCodeSchool.book.controller;

import java.util.List;
// import java.util.Optional;

import org.hibernate.mapping.Map;
// import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wildCodeSchool.book.entity.Book;
import com.wildCodeSchool.book.repository.BookRepository;

// import org.springframework.ui.Model;

@RestController
public class BookController {

    @Autowired
    private BookRepository repository;

    // TODO : find all books

    @GetMapping("/books")
    public List<Book> index() {
        return repository.findAll();
    }

    // TODO : find one book by id

    @GetMapping("/book/{id}")
    public Book show(@PathVariable int id) {
        return repository.findById((long) id).get();
    }

    // TODO : create or update a book

    @PostMapping("/books")
    public Book create(@RequestBody Book book) {
        return repository.save(book);
    }

    // TODO : update a book

    @PutMapping("/book/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book) {
        Book bookToUpdate = repository.findById((long) id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return repository.save(bookToUpdate);
    }

    // TODO : delete a book

    @GetMapping("/book/delete/{id}")
    public boolean delete(@PathVariable int id) {
        repository.deleteById((long) id);
        return true;
    }

    // TODO : search

    @PostMapping("/book/search")
    public List<Book> search(@RequestBody java.util.Map<String, String> body) {
        String titleTerm = body.get("title");
        String descriptionTerm = body.get("description");
        return repository.findByTitleContainingOrDescriptionContaining(titleTerm, descriptionTerm);
    }
}