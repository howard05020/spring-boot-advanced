package com.howard.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.howard.domain.User;
import com.howard.domain.UserRepository;
import com.howard.form.UserForm;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "register";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	@PostMapping("/register")
	public String registerPost(@Valid UserForm userForm, BindingResult result) {
		if (!userForm.confirmPassword()) {
			result.rejectValue("confirmPassword", "confirmError", "密碼與確認密碼不符合");
		}

		if (result.hasErrors()) {
			return "register";
		}

		User user = userForm.convertToUser();
		userRepository.save(user);
		return "redirect:/login";
	}
	
	@GetMapping("/exception")
	public String textException() {
		throw new RuntimeException("測試異常處理");
	}
}
