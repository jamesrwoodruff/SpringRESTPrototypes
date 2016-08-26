package com.jrw;
 
import java.net.URI;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jrw.model.Book;
 
public class SpringRestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/SpringRESTWithAuthPrototype01"; 
 
    /*
     * Add HTTP Authorization header, using Basic-Authentication to send user-credentials.
     */
    private static HttpHeaders getHeaders(){
    	String plainCredentials="user1-admin:monkey1";
    	String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Authorization", "Basic " + base64Credentials);
    	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    	return headers;
    }
    
    /*
     * Send a GET request to get list of all books.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static void listAllBooks(){
        System.out.println("\nTesting listAllBooks API-----------");
        RestTemplate restTemplate = new RestTemplate(); 
        
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<List> response = restTemplate.exchange(REST_SERVICE_URI+"/book/", HttpMethod.GET, request, List.class);
        List<LinkedHashMap<String, Object>> booksMap = (List<LinkedHashMap<String, Object>>)response.getBody();
        
        if(booksMap!=null){
            for(LinkedHashMap<String, Object> map : booksMap){
                System.out.println("Book : id="+map.get("id")+", Name="+map.get("name")+", Author="+map.get("author")+", Year="+map.get("year"));
            }
        } else {
            System.out.println("No book exists!");
        }
    }
     
    /*
     * Send a GET request to get a specific book.
     */
    private static void getBook(){
        System.out.println("\nTesting getBook API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        ResponseEntity<Book> response = restTemplate.exchange(REST_SERVICE_URI+"/book/1", HttpMethod.GET, request, Book.class);
        Book book = response.getBody();
        System.out.println(book);
    }
     
    /*
     * Send a POST request to create a new book.
     */
    private static void createBook() {
        System.out.println("\nTesting create Book API----------");
        RestTemplate restTemplate = new RestTemplate();
        Book book = new Book(0,"Some Name","Some Author",2016);
        HttpEntity<Object> request = new HttpEntity<Object>(book, getHeaders());
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/book/", request, Book.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    /*
     * Send a PUT request to update an existing book.
     */
    private static void updateBook() {
        System.out.println("\nTesting update Book API----------");
        RestTemplate restTemplate = new RestTemplate();
        Book book  = new Book(1,"Another Name","Another Author", 2016);
        HttpEntity<Object> request = new HttpEntity<Object>(book, getHeaders());
        ResponseEntity<Book> response = restTemplate.exchange(REST_SERVICE_URI+"/book/1", HttpMethod.PUT, request, Book.class);
        System.out.println(response.getBody());
    }
 
    /*
     * Send a DELETE request to delete a specific book.
     */
    private static void deleteBook() {
        System.out.println("\nTesting delete Book API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        restTemplate.exchange(REST_SERVICE_URI+"/book/3", HttpMethod.DELETE, request, Book.class);
    }
 
 
    /*
     * Send a DELETE request to delete all books.
     */
    private static void deleteAllBooks() {
        System.out.println("\nTesting all delete Books API----------");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(getHeaders());
        restTemplate.exchange(REST_SERVICE_URI+"/book/", HttpMethod.DELETE, request, Book.class);
    }
 

    public static void main(String args[]){
        
    	listAllBooks();

        getBook();

        createBook();
        listAllBooks();

        updateBook();
        listAllBooks();

        deleteBook();
        listAllBooks();

        deleteAllBooks();
        listAllBooks();
    }
}