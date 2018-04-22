package com.jrw.service;

import java.util.List;

import com.jrw.model.Book;

public interface BookService {

	Book findById(long id);
	Book findByName(String name);	
	void saveBook(Book book);
	void updateBook(Book book);	
	void deleteBookById(long id);
	List<Book> findAllBooks(); 	
	void deleteAllBooks();
	public boolean isBookExist(Book book);	

}
