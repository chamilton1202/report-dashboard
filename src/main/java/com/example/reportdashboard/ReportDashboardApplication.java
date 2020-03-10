package com.example.reportdashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.reportdashboard.model.Book;
import com.example.reportdashboard.service.BookService;

@SpringBootApplication
public class ReportDashboardApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(ReportDashboardApplication.class);
	
	@Autowired
	private BookService bookService;
	
	public static void main(String[] args) {
		SpringApplication.run(ReportDashboardApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Running the ReportDashboard Application...");
		
		bookService.saveBook(new Book("Java"));
		bookService.saveBook(new Book("Node"));
		bookService.saveBook(new Book("Python"));
		bookService.saveBook(new Book("Camel"));
		
		log.info("FindAll");
		bookService.findAllBooks().forEach(x -> log.info("Book: " + x));
		
		log.info("FindById");
		bookService.findBookById(1L).ifPresent(x -> log.info("Book: " + x));
		
		log.info("FindByName");
		bookService.findBookByName("Camel").forEach(x -> log.info("Book: " + x));
		
	}

}
