package com.example.reportdashboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reportdashboard.model.Book;
import com.example.reportdashboard.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepos;
	
	@Override
	public List<Book> findBookByName(String name) {
		return bookRepos.findByName(name);
	}

	@Override
	public void saveBook(Book book) {
		bookRepos.save(book);		
	}

	@Override
	public List<Book> findAllBooks() {
		return bookRepos.findAll();
	}

	@Override
	public Optional<Book> findBookById(Long id) {
		return bookRepos.findById(id);
	}

	
}
