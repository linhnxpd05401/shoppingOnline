package edu.poly.shop.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionDto {
	private Integer promotionId;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;
	
	@NotNull
	private Integer categoryId;
	
	private String image;
	
	private MultipartFile imageFile;
	
}
