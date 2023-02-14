package edu.poly.shop.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import edu.poly.shop.domain.Category;
import edu.poly.shop.domain.Product;
import edu.poly.shop.model.CategoryDto;
import edu.poly.shop.model.ProductDto;
import edu.poly.shop.service.CategoryService;
import edu.poly.shop.service.ProductService;
import edu.poly.shop.service.StorageService;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	StorageService storageService;
	
	@ModelAttribute("categories")
	public List<CategoryDto> getCategories() {
		return categoryService.findAll().stream().map(item -> {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping(value = { "productManagement", "page/{pageNo}" })
	private String findPaginated(@PathVariable(value = "pageNo") Optional<Integer> pageNo, Model model,
			@RequestParam("message") Optional<String> message,@RequestParam("id") Optional<Integer> id) throws IOException {
		int pageSize = 10;
		Page<Product> page;
		if (pageNo.isPresent()) {
			page = productService.findPaginated(pageNo.get(), pageSize);
			model.addAttribute("currentPage", pageNo.get());
		} else {
			page = productService.findPaginated(1, pageSize);
			model.addAttribute("currentPage", 1);
		}
		
		if (id.isPresent()) {
			Optional<Product> opt = productService.findById(id.get()); 
			ProductDto dto = new ProductDto();
			if(opt.isPresent()) {
				Product entity = opt.get();
				if(!StringUtils.isEmpty(opt.get().getImage())) {
					storageService.delete(opt.get().getImage());
				}
				BeanUtils.copyProperties(entity, dto);
				model.addAttribute("product", dto);
			}
		} else {
			model.addAttribute("product", new ProductDto());
		}
		
		List<Product> lisProducts = page.getContent();
		model.addAttribute("products", lisProducts);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("productActive", "active");
		if (message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		return "admin/products/product";
	}
	
	@RequestMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Integer id, ModelMap model) {
		model.addAttribute("id", id);
		return new ModelAndView("redirect:/admin/products/productManagement", model);
	}

	@SuppressWarnings("deprecation")
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int productId, Model model) throws IOException {
		Optional<Product> opt = productService.findById(productId);
		
		if(opt.isPresent()) {
			if(!StringUtils.isEmpty(opt.get().getImage())) {
				storageService.delete(opt.get().getImage());
			}
			productService.delete(opt.get());
			model.addAttribute("message", "Success");
		}else {
			model.addAttribute("message", "Failed");
		}
		return "redirect:/admin/products/productManagement";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("product") ProductDto dto,
			BindingResult result) {
		
		if (result.hasErrors()) {
			model.addAttribute("message", "Failed");
			return new ModelAndView("redirect:/admin/products/productManagement", model);
		}
		Product entity = new Product();
		BeanUtils.copyProperties(dto, entity);
		Category category = new Category();
		category.setCategoryId(dto.getCategoryId());
		entity.setCategory(category);
		
		if(!dto.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			entity.setImage(storageService.getStorageFileName(dto.getImageFile(), uuString));
			storageService.store(dto.getImageFile(), entity.getImage());
		}
		
		productService.save(entity);
		model.addAttribute("message", "Success");
		return new ModelAndView("redirect:/admin/products/productManagement", model);
	}
}
