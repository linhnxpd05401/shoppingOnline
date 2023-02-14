package edu.poly.shop.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Share {
	private Integer shareId;
	private Integer customerId;
	private Integer productId;
	private Date sharedate;
	private int flatform;
}
