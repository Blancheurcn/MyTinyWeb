package com.example.MyTinyWeb;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebController implements WebMvcConfigurer {
	@Autowired
	private UserRepository userRepository;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/initial").setViewName("initial");
	}

	@GetMapping("/all")
	public @ResponseBody Iterable<MyTinyUser> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping( "/")
	public String showFrom(MyTinyUser user){
		return  "initial";
	}

	@PostMapping("/dispose")
	public String addUser(@Valid MyTinyUser user, BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			return	"register";
		}
		userRepository.save(user);
		return "initial";
	}

	@PostMapping("CheckRegister")
	public String checkUser(@Valid MyTinyUser user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
			return "initial";

	}

	@GetMapping("register")
	public String ToRegister(Model model) {
		model.addAttribute("user", new MyTinyUser());
		return "register";
	}


	@GetMapping("login")
	public String ToLogin(MyTinyUser user){return "login";}
}
