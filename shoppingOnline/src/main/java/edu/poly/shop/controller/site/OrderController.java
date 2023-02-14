package edu.poly.shop.controller.site;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Favorite;
import edu.poly.shop.domain.Order;
import edu.poly.shop.service.FavoriteService;
import edu.poly.shop.service.OrderService;
import edu.poly.shop.service.UserService;

@Controller
@RequestMapping("/site/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	FavoriteService favoriteService;
	
	@GetMapping("myOrder")
	public String getAccount(Model model) {
		Integer customerId = Integer.parseInt(session.getAttribute("customer").toString());
		List<Order> orders = orderService.findByCustomer(customerId);
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
		}
		model.addAttribute("orders", orders);
		
		return "/site/orders/order";
	}
}
