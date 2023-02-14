package edu.poly.shop.controller.site;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.CartItem;
import edu.poly.shop.domain.Customer;
import edu.poly.shop.domain.Delivery;
import edu.poly.shop.domain.Favorite;
import edu.poly.shop.domain.Order;
import edu.poly.shop.domain.OrderDetail;
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.model.DeliveryDto;
import edu.poly.shop.service.CartItemsService;
import edu.poly.shop.service.DeliveryService;
import edu.poly.shop.service.FavoriteService;
import edu.poly.shop.service.OrderDetailService;
import edu.poly.shop.service.OrderService;
import edu.poly.shop.service.UserService;

@Controller
@RequestMapping("/site/cart")
public class CheckoutController {

	@Autowired
	HttpSession session;

	@Autowired
	DeliveryService deliveryService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderDetailService orderDetailService;

	@Autowired
	UserService userService;
	
	@Autowired
	CartItemsService cartItemsService;
	
	@Autowired
	FavoriteService favoriteService;

	@GetMapping("checkout")
	public String saveCartDetail(Model model) {
		double total = 0.0;
		List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
		for (CartItem cartItem : cartItems) {
			total = total + cartItem.getSubTotal();
		}
		Order order = new Order();
		model.addAttribute("cartAmount", total);
		model.addAttribute("charges", 100);
		model.addAttribute("discount", (total + 100) * 0.1);
		model.addAttribute("total", ((total + 100) - (total + 100) * 0.1));
		Delivery entity = new Delivery();
		model.addAttribute("delivery", entity);
		
		Integer cid = Integer.parseInt(session.getAttribute("customer").toString());
		
		Optional<Customer> customer = userService.findById(cid);
		
		List<Favorite> fByc = favoriteService.findByCustomer(customer.get());
		if (fByc.size() > 0) {
			model.addAttribute("empty", false);
			model.addAttribute("favoriteNum", fByc.size());
		} else {
			model.addAttribute("empty", true);
		}
		return "site/checkout/checkout";
	}

	@RequestMapping("order")
	public ModelAndView getDeliveryInfo(ModelMap model, @Valid @ModelAttribute("delivery") DeliveryDto dto,
			BindingResult result) {

		double cartAmount = 0.0;
		List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");

		for (CartItem cartItem : cartItems) {
			cartAmount = cartAmount + cartItem.getSubTotal();
		}
		Order order = new Order();
		double total = (cartAmount + 100) - (cartAmount + 100) * 0.1;

		Integer customerId = Integer.parseInt(session.getAttribute("customer").toString());
		Optional<Customer> customer = userService.findById(customerId);
		if (customer.isPresent()) {
			order.setOrderedDate(new Date());
			order.setAmount(total);
			order.setCustomer(customer.get());
			orderService.save(order);
		}

//		if (result.hasErrors()) {
//			model.addAttribute("message", "Failed");
//			return new ModelAndView("redirect:/admin/accounts/accountManagement", model);
//		}
		
		List<Order> orders = orderService.findByCustomerAndDate(customerId, new Date());
		Order order2 = orders.get(orders.size() - 1);
		Delivery entity = new Delivery();
		entity.setOrder(order2);
		BeanUtils.copyProperties(dto, entity);
		OrderDetail orderDetail = new OrderDetail();
		for(CartItem item : cartItems) {
			orderDetail.setProduct(item.getProduct());
			orderDetail.setQuantity(item.getQuantity());
			orderDetail.setOrder(order2);
			orderDetailService.save(orderDetail);
		}
		deliveryService.save(entity);
		model.addAttribute("message", "Ordered Successfully.");
		cartItemsService.deleteByCustomer(customerId);
		return new ModelAndView("redirect:/site/home/homePage", model);
	}
	
}
