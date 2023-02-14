package edu.poly.shop.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.mail.MailSessionDefinition;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Account;
import edu.poly.shop.domain.Category;
import edu.poly.shop.model.AccountDto;
import edu.poly.shop.model.CategoryDto;
import edu.poly.shop.service.AccountService;
import edu.poly.shop.service.CategoryService;

@Controller
@RequestMapping("admin/accounts")
public class AccountController {

	@Autowired
	AccountService accountService;

//	@RequestMapping("categoryManagement")
//	public String viewHome(Model model) {
//		return findPaginated(1, model);
//	}

	@GetMapping(value = { "accountManagement", "page/{pageNo}" })
	private String findPaginated(@PathVariable(value = "pageNo") Optional<Integer> pageNo, Model model,
			@RequestParam("message") Optional<String> message, @RequestParam("username") Optional<String> username) {
		int pageSize = 5;
		Page<Account> page;
		if (pageNo.isPresent()) {
			page = accountService.findPaginated(pageNo.get(), pageSize);
			model.addAttribute("currentPage", pageNo.get());
		} else {
			page = accountService.findPaginated(1, pageSize);
			model.addAttribute("currentPage", 1);
		}
		if (username.isPresent()) {
			Optional<Account> opt = accountService.findById(username.get()); 
			AccountDto dto = new AccountDto();
			if(opt.isPresent()) {
				Account entity = opt.get();
				BeanUtils.copyProperties(entity, dto);
				dto.setPassword("");
				model.addAttribute("account", dto);
			}
		} else {
			model.addAttribute("account", new AccountDto());
		}
		List<Account> lisAccount = page.getContent();
		model.addAttribute("accounts", lisAccount);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("accountActive", "active");
		if (message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		return "admin/accounts/account";
	}

	@RequestMapping("edit/{username}")
	public ModelAndView edit(@PathVariable("username") String username, ModelMap model) {
		model.addAttribute("username", username);
		return new ModelAndView("redirect:/admin/accounts/accountManagement", model);
	}

	@GetMapping("delete/{username}")
	public String delete(@PathVariable("username") String username, Model model) {
		accountService.deleteById(username);
		return "redirect:/admin/accounts/accountManagement";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("account") AccountDto dto,
			BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("message", "Failed");
			return new ModelAndView("redirect:/admin/accounts/accountManagement", model);
		}

		Account entity = new Account();
		BeanUtils.copyProperties(dto, entity);
		accountService.save(entity);
		model.addAttribute("message", "Success");
		return new ModelAndView("redirect:/admin/accounts/accountManagement", model);
	}

}
