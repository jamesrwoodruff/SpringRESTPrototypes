package com.jrw.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")
public class Book {

	private long id;
	private String name;
	private String author;
	private int year;

	public Book() {
		id=0;
	}
	
	public Book(long id, String name, String author, int year) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.year = year;
	}

	@XmlElement
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}	

	@XmlElement
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
}
