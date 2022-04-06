package com.howard.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.howard.domain.Book;
import com.howard.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/{id}")
	public String getBookById(@PathVariable Long id, Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		return "book";
	}

}
