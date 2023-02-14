package edu.poly.shop.controller.site;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.shop.domain.CartItem;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Favorite;
import edu.poly.shop.domain.Product;
import edu.poly.shop.service.CartItemsService;
import edu.poly.shop.service.FavoriteService;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.UserService;

@Controller
@RequestMapping("/site/contact")
public class contactController {

	@Autowired
	HttpSession session;

	@Autowired
	UserService userService;

	@Autowired
	CartItemsService cartItemsService;

	@Autowired
	FavoriteService favoriteService;

	@GetMapping(value = { "contactForm" })
	public String getHome(Model model) {
		if (session.getAttribute("customer") != null) {
			Integer cid = Integer.parseInt(session.getAttribute("customer").toString());
			Customer customer = userService.findById(cid).get();
			List<CartItem> cartItems = cartItemsService.findByCustomer(customer);
			if (cartItems.size() > 0) {
				model.addAttribute("cartList", cartItems);
				model.addAttribute("itemQuantity", cartItems.size());
			}
			model.addAttribute("customer", cid);

			List<Favorite> fByc = favoriteService.findByCustomer(customer);
			if (fByc.size() > 0) {
				model.addAttribute("empty", false);
				model.addAttribute("favoriteNum", fByc.size());
			} else {
				model.addAttribute("empty", true);	
			}
		}
		return "site/contact/contact";
	}
	
	
//	@PostMapping("sendEmail")
//	public String sendEmail(Model model) {
//		reutn
//	}

}
