package com.jrw.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrw.model.Book;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Book> books;
	
	static{
		books = populateDummyBooks();
	}

	public List<Book> findAllBooks() {
		return books;
	}
	
	public Book findById(long id) {
		for(Book book : books){
			if(book.getId() == id){
				return book;
			}
		}
		return null;
	}
	
	public Book findByName(String name) {
		for(Book book : books){
			if(book.getName().equalsIgnoreCase(name)){
				return book;
			}
		}
		return null;
	}
	
	public void saveBook(Book book) {
		book.setId(counter.incrementAndGet());
		books.add(book);
	}

	public void updateBook(Book book) {
		int index = books.indexOf(book);
		books.set(index, book);
	}

	public void deleteBookById(long id) {
		
		for (Iterator<Book> iterator = books.iterator(); iterator.hasNext(); ) {
		    Book book = iterator.next();
		    if (book.getId() == id) {
		        iterator.remove();
		    }
		}
	}

	public boolean isBookExist(Book book) {
		return findByName(book.getName())!=null;
	}

	private static List<Book> populateDummyBooks(){
		List<Book> books = new ArrayList<Book>();
		books.add(new Book(counter.incrementAndGet(),"A Tale of Two Cities", "Alan Alanson", 2001));
		books.add(new Book(counter.incrementAndGet(),"Beowulf", "Bob Bobberson", 2002));
		books.add(new Book(counter.incrementAndGet(),"Candide", "Carl Carlson", 2003));
		books.add(new Book(counter.incrementAndGet(),"Doctor Faustus", "Dan Danielson", 2004));
		return books;
	}

	public void deleteAllBooks() {
		books.clear();
	}

}
