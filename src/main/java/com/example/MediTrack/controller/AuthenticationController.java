/*package com.example.MediTrack.controller;



import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.MediTrack.model.User;
import com.example.MediTrack.service.MyUserDetails;
import com.example.MediTrack.service.UserDetailsServiceImpl;
//import com.example.MediTrack.service.UserService;
import com.example.MediTrack.service.UserService;

@RestController
public class AuthenticationController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder encoder;
		
	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	@GetMapping("/login")
	public ModelAndView showlogin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Login");
		return modelAndView;
	}
	
	@GetMapping("/loginTest")
	public ResponseEntity<String> loginTest(@RequestParam String userName,@RequestParam String password) {
	User user=userService.getUser(userName);
	System.out.println("password: "+ user.getPassword());
	if (encoder.matches(password,user.getPassword())) {
		return ResponseEntity.ok("LOGGED IN");
	}else {
		return ResponseEntity.ok("NOT LOGGED IN");
	}
	}
	
	@GetMapping("/Test")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("Hi Its working");
	}
	
	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user",user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@PostMapping("/register")
	public ModelAndView registerUser(@Validated User user, BindingResult bindingResult,ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		//check for the validations
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage","Please correct the errors in the registration form!");
			modelMap.addAttribute("bindingResult",bindingResult);
		}
		// we will save the user if, no binding errors
		else if(userService.isUserAlreadyPresent(user)){
			modelAndView.addObject("successMessage","user already exists!");
		}
		else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage","user is registered succesfully!");
		}
		modelAndView.addObject("user",new User());
		modelAndView.setViewName("registration");
		return modelAndView;
	}

}
*/