package com.bookStore.controller;

import com.bookStore.entity.Book;
import com.bookStore.entity.MyBookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @Autowired
    MyBookListService myBookListService;

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/book_register")
    public String addBook(){
        return "book_register";
    }

    @GetMapping("/available_books")
    public ModelAndView availableBook(){
      List<Book>bookList= bookService.getAllBook();
//      ModelAndView view=new ModelAndView();
//      view.setViewName("bookList");
//      view.addObject("book",bookList);
//      return view;
        return new ModelAndView("bookList","book",bookList);
    }
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book){
        bookService.addBook(book);
        return "redirect:/available_books";
    }
    @GetMapping("/my_books")
    public String myBooks(Model model){

        List<MyBookList>bookLists=myBookListService.getAllByBooks();
        model.addAttribute("book",bookLists);
        return "myBooks";
    }

    @RequestMapping("/myList/{id}")
    public String getMyList(@PathVariable int id){
    Book book=bookService.getBookById(id);
    MyBookList bookList=new MyBookList(book.getId(),book.getName(),book.getAuthor(),book.getPrice());
    myBookListService.saveBooks(bookList);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable int id,Model model){
        Book book=bookService.getBookById(id);
        model.addAttribute("book",book);
        return "bookEdit";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
        return "redirect:/available_books";
    }



}
