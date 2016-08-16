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

/*
 * This is a REST controller class that will serve book resources in a REST API.
 */
@RestController
public class BookController {

	@Autowired
	BookService bookService;
	
	/*
	 * Retrieve all books.
	 */	
	@RequestMapping(value = "/book/", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listAllBooks() {

		System.out.println("Listing all books");
		
		List<Book> books = bookService.findAllBooks();
		
		if(books.isEmpty()){
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT); // Alternatively, you may want to return HttpStatus.NOT_FOUND.
		}
		
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}


	/*
	 * Retrieve a single book by id (in json or xml format).
	 * The produces attribute indicates that this method will handle requests where JSON or XML output is expected.
	 * That is, requests whose Accept header includes application/json or application/xml.
	 * Any other kind of request will not be handled by this method. It will either be handled by another
	 * handler method, if one exists, or the client will be sent an HTTP 406 (Not Acceptable) response.
	 */
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET, 
			produces={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Book> getBook(@PathVariable("id") long id) {

		System.out.println("Retrieving book with id " + id);
		
		Book book = bookService.findById(id);
		
		if (book == null) {
			System.out.println("Book with id " + id + " not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	
	/*
	 * Create and post a new book.
	 * @RequestBody tells spring to find a message converter to convert a resource representation
	 * 	coming from a client into an object.
	 * The body of the POST request is expected to carry a resource representation for a Book.
	 * Because the Book parameter is annotated with @Requestbody, Spring will look at the Content-Type
	 * 	header of the request and try to find a message converter that can convert the request body into a Book.
	 * UriComponentsBuilder lets you build a UriComponents instance by specifying the various URI components
	 * 	one piece at a time. To use it,  you just ask for it as a parameter to the handler method.
	 */
	@RequestMapping(value = "/book/", method = RequestMethod.POST)
	public ResponseEntity<Void> createBook(@RequestBody Book book, 	UriComponentsBuilder ucb) {
		
		System.out.println("Creating Book " + book.getName());

		if (bookService.isBookExist(book)) {
			System.out.println("A Book with the name " + book.getName() + " already exists");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		bookService.saveBook(book);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("/book/{id}")
				.buildAndExpand(book.getId())
				.toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	
	/*
	 * Update an existing book.
	 * @RequestBody tells spring to find a message converter to convert a resource representation
	 * 	coming from a client into an object.
	 * The body of the POST request is expected to carry a resource representation for a Book.
	 * Because the Book parameter is annotated with @Requestbody, Spring will look at the Content-Type
	 * 	header of the request and try to find a message converter that can convert the request body into a Book.
	 */
	@RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {

		System.out.println("Updating Book " + id);
		
		Book abook = bookService.findById(id);
		
		if (abook==null) {
			System.out.println("Book with id " + id + " not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}

		abook.setName(book.getName());
		abook.setAuthor(book.getAuthor());
		abook.setYear(book.getYear());
		
		bookService.updateBook(abook);
		return new ResponseEntity<Book>(abook, HttpStatus.OK);
	}


	/*
	 * Delete an existing book.
	 */
	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteBook(@PathVariable("id") long id) {
		
		System.out.println("Retrieving & deleting Book with id " + id);

		Book book = bookService.findById(id);

		if (book == null) {
			System.out.println("Unable to delete. Book with id " + id + " not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}

		bookService.deleteBookById(id);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}

	
	/*
	 * Delete all books.
	 */
	@RequestMapping(value = "/book/", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteAllBooks() {
		
		System.out.println("Deleting All Books");

		bookService.deleteAllBooks();
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}
}
