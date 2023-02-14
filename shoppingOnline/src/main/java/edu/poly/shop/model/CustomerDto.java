package edu.poly.shop.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
	
	@NotNull
	private Integer customerId;
	
	@Length(min = 3)
	private String name;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String address;
	
	@NotEmpty
	@Length(min = 6, max = 16)
	private String password;
	
	@Length(min = 10, max =10)
	private String phone;
	private Date registeredDate;
	private short status;
}
