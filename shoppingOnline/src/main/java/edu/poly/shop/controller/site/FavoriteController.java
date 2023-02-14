package edu.poly.shop.controller.site;

import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Favorite;
import edu.poly.shop.domain.Product;
import edu.poly.shop.service.FavoriteService;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.UserService;

@Controller
@RequestMapping("/site/favorite")
public class FavoriteController {

	@Autowired
	HttpSession session;

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	FavoriteService favoriteService;

	@GetMapping("getFavoriteItems")
	public String getFavorList() {

		Integer cid = Integer.parseInt(session.getAttribute("customer").toString());

		Optional<Customer> optCustomer = userService.findById(cid);

		if (optCustomer.isPresent()) {
			List<Favorite> wishlist = favoriteService.findByCustomer(optCustomer.get());
		}

		return "site/home";
	}

	@RequestMapping("addItemToFavorList/{productId}")
	public String addToList(ModelMap model, @PathVariable("productId") Integer pid) {
		Integer cid = Integer.parseInt(session.getAttribute("customer").toString());

		Optional<Customer> optCustomer = userService.findById(cid);

		Optional<Product> optProduct = productService.findById(pid);

		Favorite favorite = new Favorite();
		if (optCustomer.isPresent() && optProduct.isPresent()) {
			favorite.setFavoritedDate(new Date());
			favorite.setCustomer(optCustomer.get());
			favorite.setProduct(optProduct.get());
			favoriteService.save(favorite);
		}


		return "redirect:/site/product/productDetail/" + pid;
	}

	@RequestMapping("removeItemToFavorList/{productId}")
	public String removeToList(ModelMap model, @PathVariable("productId") Integer pid) {
		Integer cid = Integer.parseInt(session.getAttribute("customer").toString());

		Optional<Customer> optCustomer = userService.findById(cid);

		Optional<Product> optProduct = productService.findById(pid);

		List<Favorite> favorites = favoriteService.findByCustomerAndProduct(cid, pid);

		favoriteService.delete(favorites.get(0));

		return "redirect:/site/product/productDetail/" + pid;
	}

}
