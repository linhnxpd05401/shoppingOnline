package edu.poly.shop.model;

import java.util.Date;

import javax.mail.Multipart;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	private Integer productId;
	private String name;
	private int quantity;
	private double unitPrice;
	private String image;
	private MultipartFile imageFile;
	private String description;
	private double discount;
	private Date createdDate;
	private short status;
	private Integer categoryId;
}
