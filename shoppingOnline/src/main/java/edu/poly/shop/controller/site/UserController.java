package edu.poly.shop.controller.site;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Favorite;
import edu.poly.shop.model.CustomerDto;
import edu.poly.shop.service.FavoriteService;
import edu.poly.shop.service.UserService;

@Controller
@RequestMapping("/site/account")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	FavoriteService favoriteService;
	
	@GetMapping("myAccount")
	public String getAccount(Model model) {
		Integer customerId = Integer.parseInt(session.getAttribute("customer").toString());
		Optional<Customer> customer = userService.findById(customerId);
		
		if(customer.isPresent()) {
			model.addAttribute("customer", customer.get());
			List<Favorite> fByc = favoriteService.findByCustomer(customer.get());
			if (fByc.size() > 0) {
				model.addAttribute("empty", false);
				model.addAttribute("favoriteNum", fByc.size());
			} else {
				model.addAttribute("empty", true);
			}
		}else {
			model.addAttribute("customer", new Customer());
		}
		return "/site/users/account";
	}
	
	@PostMapping("updateMyAccount")
	public ModelAndView updateAccount(ModelMap model, @Valid @ModelAttribute("customer") CustomerDto dto,
			BindingResult result) {

//		if (result.hasErrors()) {
//			model.addAttribute("message", "Failed");
//			return new ModelAndView("redirect:/admin/accounts/accountManagement", model);
//		}

		Customer entity = new Customer();
		BeanUtils.copyProperties(dto, entity);
		userService.save(entity);
		model.addAttribute("message", "Success");
		return new ModelAndView("redirect:/site/myAccount", model);
	}
}
