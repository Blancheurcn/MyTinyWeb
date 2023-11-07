package com.example.MyTinyWeb;

import jakarta.validation.Valid;
import org.hibernate.cache.internal.StandardTimestampsCacheFactory;
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
		return "login";
	}

	@PostMapping("login")
	public String CheckLogin(String name,String password,Model model){
		MyTinyUser user = myTinyUserRepository.findByNameAndPassword(name, password);
		if (user == null) {
			return "login";
		} else {
			model.addAttribute("list",myTinyUserRepository.findAll());
			return "MyTinyWeb";
		}

	}
}
