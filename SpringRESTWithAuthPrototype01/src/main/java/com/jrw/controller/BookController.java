package com.jrw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.jrw.model.Book;
import com.jrw.service.BookService;

@RestController
public class BookController {

	@Autowired
	BookService bookService;  //Service which will do all data retrieval/manipulation work
	
	/*
	 * Retrieve all books
	 */	
	@RequestMapping(value = "/book/", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listAllBooks() {
		List<Book> books = bookService.findAllBooks();
		if(books.isEmpty()){
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}


	/*
	 * Retrieve single book by id
	 */
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> getBook(@PathVariable("id") long id) {
		System.out.println("Fetching Book with id " + id);
		Book book = bookService.findById(id);
		if (book == null) {
			System.out.println("Book with id " + id + " not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	
	
	/*
	 * Create a new book
	 */
	@RequestMapping(value = "/book/", method = RequestMethod.POST)
	public ResponseEntity<Void> createBook(@RequestBody Book book, 	UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Book " + book.getName());

		if (bookService.isBookExist(book)) {
			System.out.println("A Book with name " + book.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		bookService.saveBook(book);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(book.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	
	/*
	 * Update an existing book
	 */
	@RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
		System.out.println("Updating Book " + id);
		
		Book currentBook = bookService.findById(id);
		
		if (currentBook==null) {
			System.out.println("Book with id " + id + " not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}

		currentBook.setName(book.getName());
		currentBook.setAuthor(book.getAuthor());
		currentBook.setYear(book.getYear());
		
		bookService.updateBook(currentBook);
		return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
	}


	/*
	 * Delete an existing book
	 */
	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteBook(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Book with id " + id);

		Book book = bookService.findById(id);
		if (book == null) {
			System.out.println("Unable to delete. Book with id " + id + " not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}

		bookService.deleteBookById(id);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}

	
	/*
	 * Delete all books
	 */
	@RequestMapping(value = "/book/", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteAllBooks() {
		System.out.println("Deleting All Books");

		bookService.deleteAllBooks();
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}
}
