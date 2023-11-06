package com.example.MyTinyWeb;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {
	@Autowired
	private UserRepository userRepository;


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

	@GetMapping("register")
	public String ToRegister(MyTinyUser user){
		return "register";
	}

	@GetMapping("login")
	public String ToLogin(MyTinyUser user){return  "login";}
}
