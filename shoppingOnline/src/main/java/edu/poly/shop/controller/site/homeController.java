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
@RequestMapping("/site/home")
public class homeController {

	@Autowired
	ProductService productService;

	@Autowired
	CartItemsService cartItemsService;

	@Autowired
	HttpSession session;

	@Autowired
	UserService userService;
	
	@Autowired
	FavoriteService favoriteService;

	@RequestMapping(value = { "homePage", "page/{pageNo}" })
	public String getHome(@PathVariable("pageNo") Optional<Integer> pageNo, Model model,
			@RequestParam("message") Optional<String> mess) {

		int pageSize = 8;
		Page<Product> page;
		if (pageNo.isPresent()) {
			page = productService.findPaginated(pageNo.get(), pageSize);
			model.addAttribute("currentPage", pageNo.get());
		} else {
			page = productService.findPaginated(1, pageSize);
			model.addAttribute("currentPage", 1);
		}

		if (mess.isPresent()) {
			model.addAttribute("message", mess.get());
		}

		if(session.getAttribute("customer")!= null) {
			Integer cid = Integer.parseInt(session.getAttribute("customer").toString());
			Customer customer = userService.findById(cid).get();
			List<CartItem> cartItems = cartItemsService.findByCustomer(customer);
			if(cartItems.size() > 0) {
				model.addAttribute("cartList", cartItems);
				model.addAttribute("itemQuantity", cartItems.size());				
			}
			model.addAttribute("customer", cid);
			List<Favorite> fByc = favoriteService.findByCustomer(customer);
			if (fByc.size() > 0) {
				model.addAttribute("empty", false);
				model.addAttribute("favoriteNum", fByc.size());
				model.addAttribute("favorites", fByc);
			} else {
				model.addAttribute("empty", true);
			}
		}

		List<Product> lisProducts = page.getContent();
		model.addAttribute("products", lisProducts);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		return "site/home";
	}

}
