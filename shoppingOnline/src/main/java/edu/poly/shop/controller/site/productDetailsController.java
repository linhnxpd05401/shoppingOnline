package edu.poly.shop.controller.site;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.poly.shop.domain.CartItem;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Favorite;
import edu.poly.shop.domain.Product;
import edu.poly.shop.service.CartItemsService;
import edu.poly.shop.service.FavoriteService;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.UserService;

@Controller
@RequestMapping("/site/product")
public class productDetailsController {

	@Autowired
	ProductService productService;

	@Autowired
	HttpSession session;

	@Autowired
	UserService userService;

	@Autowired
	CartItemsService cartItemsService;

	@Autowired
	FavoriteService favoriteService;

	@GetMapping("productDetail/{id}")
	public String getHome(@PathVariable Optional<Integer> id, Model model) {

		if (id.isPresent()) {
			Optional<Product> opt = productService.findById(id.get());
			if (opt.isPresent()) {
				model.addAttribute("product", opt.get());
			}
		}

		if (session.getAttribute("customer") != null) {
			Integer cid = Integer.parseInt(session.getAttribute("customer").toString());
			Customer customer = userService.findById(cid).get();
			List<CartItem> cartItems = cartItemsService.findByCustomer(customer);
			if (cartItems.size() > 0) {
				model.addAttribute("cartList", cartItems);
				model.addAttribute("itemQuantity", cartItems.size());
			}

			List<Favorite> favorites = favoriteService.findByCustomerAndProduct(cid, id.get());
			List<Favorite> fByc = favoriteService.findByCustomer(customer);
			if (favorites.size() > 0) {
				model.addAttribute("empty", false);
				model.addAttribute("favoriteNum", fByc.size());
			} else {
				model.addAttribute("empty", true);
			}

		}

		return "site/productDetails/product";
	}

}
