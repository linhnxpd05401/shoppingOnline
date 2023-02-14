package edu.poly.shop.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
	private Integer favoriteId;
	private Integer customerId;
	private Integer productId;
	private Date favoritedDate;
}
