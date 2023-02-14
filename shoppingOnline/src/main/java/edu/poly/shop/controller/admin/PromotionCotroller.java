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
import edu.poly.shop.domain.Promotion;
import edu.poly.shop.model.CategoryDto;
import edu.poly.shop.model.ProductDto;
import edu.poly.shop.model.PromotionDto;
import edu.poly.shop.service.CategoryService;
import edu.poly.shop.service.PromotionService;
import edu.poly.shop.service.StorageService;

@Controller
@RequestMapping("/admin/promotions")
public class PromotionCotroller {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	PromotionService promotionService;
	
	@ModelAttribute("categories")
	public List<CategoryDto> getCategories() {
		return categoryService.findAll().stream().map(item -> {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping(value = {"promotionManagement", "page/{pageNo}" })
	private String findPaginated(@PathVariable(value = "pageNo") Optional<Integer> pageNo, Model model,
			@RequestParam("message") Optional<String> message,@RequestParam("id") Optional<Integer> id) throws IOException {
		int pageSize = 10;
		Page<Promotion> page;
		if (pageNo.isPresent()) {
			page = promotionService.findPaginated(pageNo.get(), pageSize);
			model.addAttribute("currentPage", pageNo.get());
		} else {
			page = promotionService.findPaginated(1, pageSize);
			model.addAttribute("currentPage", 1);
		}
		
		if (id.isPresent()) {
			Optional<Promotion> opt = promotionService.findById(id.get()); 
			PromotionDto dto = new PromotionDto();
			if(opt.isPresent()) {
				Promotion entity = opt.get();
				if(!StringUtils.isEmpty(opt.get().getImage())) {
					storageService.delete(opt.get().getImage());
				}
				BeanUtils.copyProperties(entity, dto);
				model.addAttribute("promotion", dto);
			}
		} else {
			model.addAttribute("promotion", new PromotionDto());
		}
		
		List<Promotion> listPromotion = page.getContent();
		model.addAttribute("promotions", listPromotion);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("promotionActive", "active");
		if (message.isPresent()) {
			model.addAttribute("message", message.get());
		}
		return "admin/promotions/promotion";
	}
	
	@RequestMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Integer id, ModelMap model) {
		model.addAttribute("id", id);
		return new ModelAndView("redirect:/admin/promotions/promotionManagement", model);
	}

	@SuppressWarnings("deprecation")
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int promotionId, Model model) throws IOException {
		Optional<Promotion> opt = promotionService.findById(promotionId);
		
		if(opt.isPresent()) {
			if(!StringUtils.isEmpty(opt.get().getImage())) {
				storageService.delete(opt.get().getImage());
			}
			promotionService.delete(opt.get());
			model.addAttribute("message", "Success");
		}else {
			model.addAttribute("message", "Failed");
		}
		return "redirect:/admin/promotions/promotionManagement";
	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("promotion") PromotionDto dto,
			BindingResult result) {
		
		if (result.hasErrors()) {
			model.addAttribute("message", "Failed");
			return new ModelAndView("redirect:/admin/promotions/promotionManagement", model);
		}
		Promotion entity = new Promotion();
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
		
		promotionService.save(entity);
		model.addAttribute("message", "Success");
		return new ModelAndView("redirect:/admin/promotions/promotionManagement", model);
	}
}
