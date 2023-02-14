package edu.poly.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
	private Integer orderDetailId;
	private Integer orderId;
	private Integer productId;
	private int quantity;
	private double unitPrice;
}
