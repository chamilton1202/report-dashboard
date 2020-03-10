package com.example.reportdashboard.service;

import java.util.List;
import java.util.Optional;

import com.example.reportdashboard.model.Book;

public interface BookService {

	//CRUD Operations
	//Save
	public void saveBook(Book book);
	//FindAll
	public List<Book> findAllBooks();
	//FindById
	public Optional<Book> findBookById(Long id);
	
	//Custom Operations
	//FindByName
	public List<Book> findBookByName(String name);
	
}
