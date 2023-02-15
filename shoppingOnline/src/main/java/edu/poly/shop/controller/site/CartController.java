		package edu.poly.shop.controller.site;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.poly.shop.domain.CartItem;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Favorite;
import edu.poly.shop.service.CartItemsService;
import edu.poly.shop.service.FavoriteService;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.UserService;

@Controller
@RequestMapping("/site/cart")
public class CartController {
	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private CartItemsService cartItemsService;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private FavoriteService favoriteService;

	@GetMapping(value = { "cartlist" })
	public String getItems(Model model, @RequestParam("subTotal") Optional<Double> subtotal) {
		Integer customerId = Integer.parseInt(session.getAttribute("customer").toString());
		Optional<Customer> customer = userService.findById(customerId);
		if (customer.isPresent()) {
			List<CartItem> cartItems = cartItemsService.findByCustomer(customer.get());
			session.setAttribute("cartItems", cartItems);
			model.addAttribute("cartItems", cartItems);
			model.addAttribute("count", cartItemsService.countByCustomer(customer.get()));
		}
		
		model.addAttribute("customer", customerId);
		
		if(subtotal.isPresent()) {
			model.addAttribute("subTotal", subtotal);
		}
		
		List<Favorite> fByc = favoriteService.findByCustomer(customer.get());
		if (fByc.size() > 0) {
			model.addAttribute("empty", false);
			model.addAttribute("favoriteNum", fByc.size());
		} else {
			model.addAttribute("empty", true);
		}
		return "site/cart/cart";
	}

	@RequestMapping(value = { "addToCart/{id}/{qty}" })
	public String addToCart(@PathVariable("id") Integer id, @PathVariable("qty") Integer qty, ModelMap model) {
		Integer customerId = Integer.parseInt(session.getAttribute("customer").toString());
		Customer customer = userService.findById(customerId).get();
		Integer addedQuantity = cartItemsService.addProduct(id, qty, customer);
		return "redirect:/site/cart/cartlist";
	}
	
	@RequestMapping(value = { "update/{id}/{qty}" })
	@ResponseBody
	public String updateQuantity(@PathVariable("id") Integer id, @PathVariable("qty") Integer qty, ModelMap model) {
		Integer customerId = Integer.parseInt(session.getAttribute("customer").toString());
		Customer customer = userService.findById(customerId).get();
		double subtotal = cartItemsService.updateQuantity(id, qty, customer);
		List<CartItem> cartItems = cartItemsService.findByCustomer(customer);
		session.setAttribute("cartItems", cartItems);
		return String.valueOf(subtotal);
	}
	
	@RequestMapping(value = { "removeProduct/{id}" })
	@ResponseBody
	public String removeProduct(@PathVariable("id") Integer id, ModelMap model) {
		Integer customerId = Integer.parseInt(session.getAttribute("customer").toString());
		cartItemsService.deleteByCustomerAndProduct(customerId, id);
		
		return "The product has been removed form your shopping cart.";
	}
	    
}
