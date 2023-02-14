package edu.poly.shop.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.model.AdminLoginDto;
import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.service.AccountService;
import edu.poly.shop.service.UserService;

@Controller
public class UserLoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("loginAndRegister")
	public String loggin(ModelMap model) {
		if(session.getAttribute("customer") != null) {
			return "redirect:/site/home/homePage";
		}
		model.addAttribute("customer", new CustomerDto());
		return "site/users/loginAndRegister";
	}
	
	@PostMapping("loginAndRegister") 
	public ModelAndView login(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto, BindingResult result) {
		
//		if(result.hasErrors()) {
//			return new ModelAndView("site/users/loginAndRegister", model);
//		}
		
		Customer customer = userService.loggin(dto.getEmail(), dto.getPassword());
		if(customer == null) {
			model.addAttribute("message", "Failed");
			return new ModelAndView("site/users/loginAndRegister", model);
		}
		
		session.setAttribute("customer", customer.getCustomerId());
		Object ruri = session.getAttribute("redirect-uri");
		if(ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri);
		}
		
		return new ModelAndView("redirect:/site/home/homePage");
	}
	
	@PostMapping("register") 
	public ModelAndView register(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto, BindingResult result) {
		
//		if(result.hasErrors()) {
//			System.out.println("error");
//			return new ModelAndView("site/users/loginAndRegister", model);
//		}
		
		Customer customer = userService.findByEmail(dto.getEmail());
		if(customer != null) {
			return new ModelAndView("site/users/loginAndRegister", model);
		}else {
			Customer customer2 = new Customer();
			BeanUtils.copyProperties(dto, customer2);
			customer2.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
			userService.save(customer2);
			session.setAttribute("customer", customer2.getCustomerId());
		}
		
		Object ruri = session.getAttribute("redirect-uri");
		if(ruri != null) {
			session.removeAttribute("redirect-uri");
			return new ModelAndView("redirect:" + ruri);
		}

		return new ModelAndView("redirect:/site/home/homePage");
	}
	
	@GetMapping("logout") 
	public String logout(ModelMap model) {
		Integer customerId = Integer.parseInt(session.getAttribute("customer").toString());
		
		if(customerId != null) {
			session.removeAttribute("customer");
			session.removeAttribute("cartItems");
		}
		return "redirect:/site/home";
	}
}
