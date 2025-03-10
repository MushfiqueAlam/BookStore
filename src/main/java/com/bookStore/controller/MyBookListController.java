package com.bookStore.controller;

import com.bookStore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyBookListController {
    @Autowired
    MyBookListService myBookListService;
    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable int id){
        myBookListService.deleteById(id);
        return "redirect:/my_books";
    }
}
