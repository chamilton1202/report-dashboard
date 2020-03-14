package com.example.reportdashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.reportdashboard.model.InboundOrder;
import com.example.reportdashboard.model.OrderSearch;

@Controller
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping({"/","/index"})
	public String home(Model model) {
		log.info("Entered home()");
		model.addAttribute(new OrderSearch());
		return "index";
	}
}
