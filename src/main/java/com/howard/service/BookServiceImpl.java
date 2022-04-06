package com.howard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howard.domain.Book;
import com.howard.domain.BookRepository;
import com.howard.exception.BookNotFoundException;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;
	
	@Override
	public Book getBookById(Long id) {
		Book book = bookRepository.findById(id).get();
		if(book == null) {
			throw new BookNotFoundException("此書不存在");
		}
		return book;
	}

}
