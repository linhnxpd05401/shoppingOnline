package edu.poly.shop.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private Integer orderId;
	private Date orderedDate;
	private Integer customerId;
	private double amount;
	private short status;
}
