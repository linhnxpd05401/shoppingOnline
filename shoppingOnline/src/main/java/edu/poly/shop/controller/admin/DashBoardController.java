package edu.poly.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class DashBoardController {
	@GetMapping("home")
	public String home(Model model) {
		model.addAttribute("homeActive", "active");
		return "admin/home";
	}
}
