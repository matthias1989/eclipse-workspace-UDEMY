package com.luv2code.springdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springdemo.user.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	@Autowired
	private UserDetailsManager userDetailsManager;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {
		theModel.addAttribute("crmUser", new CrmUser());
		return "registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
											BindingResult theBindingResult, Model theModel) {
		// form validation
		if (theBindingResult.hasErrors()) {
			theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError","User name/password can not be empty.");
			return "registration-form";
		}
	
		// check the database if user already exists
		if(doesUserExist(theCrmUser.getUserName())) {
			theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError","User name already exists in the DB");
			return "registration-form";
		}
	
		// encrypt the password
		String encodedPassword = passwordEncoder.encode(theCrmUser.getPassword());
	 
		// prepend the encoding algorithm id
		encodedPassword = "{bcrypt}" + encodedPassword;

		// give user default role of "employee"
		 List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");
	 
		// create user details object
		 User tempUser = new User(theCrmUser.getUserName(), encodedPassword, authorities);
	 
		// save user in the database
		 userDetailsManager.createUser(tempUser);
	 
		return "registration-confirmation";
	}
	
	private boolean doesUserExist(String userName) {
		// check the database if the user already exists
		boolean exists = userDetailsManager.userExists(userName);
		return exists;
	}

}
