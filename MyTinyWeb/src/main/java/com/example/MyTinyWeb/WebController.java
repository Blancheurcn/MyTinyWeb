package com.example.MyTinyWeb;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
public class WebController implements WebMvcConfigurer {
	@Autowired
	private MyTinyUserRepository myTinyUserRepository;


	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/initial").setViewName("initial");
	}

	@GetMapping("/MyTinyWeb")
	public String  getAllUsers(Model model) {
		model.addAttribute("MyTinyUserList",myTinyUserRepository.findAll());
		return "MyTinyWeb";
	}

	@PostMapping("login")
	public String CheckLogin(@ModelAttribute @Valid MyTinyUser myTinyUser, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return "login";
		}
		return "MyTinyWeb";

	}

	@GetMapping( "/")
	public String showFrom(MyTinyUser user){
		return  "initial";
	}


	@PostMapping("register")
	public String checkUser(@ModelAttribute @Valid MyTinyUser myTinyUser, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
			myTinyUserRepository.save(myTinyUser);
			return "initial";

	}

	@GetMapping("register")
	public String ToRegister(MyTinyUser myTinyUser) {
		return "register";
	}


	@GetMapping("login")
	public String ToLogin(Model model){
		model.addAttribute("list",myTinyUserRepository.findAll());
		return "login";
	}
}
