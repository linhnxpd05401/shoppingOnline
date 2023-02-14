package edu.poly.shop.controller.admin;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.poly.shop.domain.Category;
import edu.poly.shop.model.CategoryDto;
import edu.poly.shop.service.CategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

//	@RequestMapping("categoryManagement")
//	public String viewHome(Model model) {
//		return findPaginated(1, model);
//	}

	@GetMapping(value = { "categoryManagement", "page/{pageNo}" })
	private String findPaginated(@PathVariable(value = "pageNo") Optional<Integer> pageNo, Model model,
			@RequestParam("message") Optional<String> message, @RequestParam("id") Optional<Integer> id) {
		int pageSize = 5;
		Page<Category> page;
		if (pageNo.isPresent()) {
			page = categoryService.findPaginated(pageNo.get(), pageSize);
			model.addAttribute("currentPage", pageNo.get());
		} else {
			page = categoryService.findPaginated(1, pageSize);
			model.addAttribute("currentPage", 1);
		}
		if (id.isPresent()) {
			Optional<Category> opt = categoryService.findById(id.get()); 
			CategoryDto dto = new CategoryDto();
			if(opt.isPresent()) {
				Category entity = opt.get();
				BeanUtils.copyProperties(entity, dto);
				model.addAttribute("category", dto);
			}
		} else {
			model.addAttribute("category", new CategoryDto());
		}
		List<Category> lisCategories = page.getContent();
		model.addAttribute("categories", lisCategories);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("categoryActive", "active");
		if (message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		return "admin/categories/category";
	}
	
//	@GetMapping("edit")
//	@ResponseBody
//	public Optional<Category> edit(Integer Id) {
//		return categoryService.findById(Id);
//	}

	@RequestMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Integer id, ModelMap model) {
		model.addAttribute("id", id);
		return new ModelAndView("redirect:/admin/categories/categoryManagement", model);
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int categoryId, Model model) {
		categoryService.deleteById(categoryId);
		return "redirect:/admin/categories/categoryManagement";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("category") CategoryDto dto,
			BindingResult result) {

		if (result.hasErrors()) {
			model.addAttribute("message", "Failed");
			return new ModelAndView("redirect:/admin/categories/categoryManagement", model);
//			return new ModelAndView("redirect:/admin/categories/categoryManagement", model);
		}

		Category entity = new Category();
		BeanUtils.copyProperties(dto, entity);
		categoryService.save(entity);
		model.addAttribute("message", "Success");
		return new ModelAndView("redirect:/admin/categories/categoryManagement", model);
	}

}
