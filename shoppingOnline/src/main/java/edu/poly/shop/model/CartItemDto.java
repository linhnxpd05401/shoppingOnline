package edu.poly.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
	private Integer cartItemId;
	private Integer productId;
	private Integer customerId;
	private int quantity;
}
