package com.bookStore.service;

import com.bookStore.entity.Book;
import com.bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public void addBook(Book book){
        bookRepository.save(book);
    }

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }
    public Book getBookById(int id){
        return bookRepository.findById(id).get();
    }

    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }
}
