package com.learning.login;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@SessionAttributes("name")
public class WelcomeController {

//	@Autowired
//	private LoginService service;// = new LoginService();
//	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		model.put("name",retrieveLoggedInUserName());
		return "welcome";
	}

	private String retrieveLoggedInUserName() {

		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();
		return principal.toString();
	}
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String handleUserLogin(ModelMap model, @RequestParam String name,
//			@RequestParam String password) {
//		
//		if(service.isUserValid(name, password)){
//			model.put("name", name);
//			model.put("password", password);
//			return "welcome";
//		}else{
//			model.put("errorMessage", "Invalid Credentials");
//			return "login";
//		}
//	}
}
