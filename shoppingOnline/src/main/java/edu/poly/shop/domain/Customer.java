package edu.poly.shop.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customers")
public class Customer implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String name;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String email;
	
	@Column(columnDefinition = "nvarchar(60) not null")
	private String password;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String address;
	
	@Column(length = 13)
	private String phone;
	
	@Temporal(TemporalType.DATE)
	private Date registeredDate;
	
	@Column(nullable = false)
	private short status;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Order> orders;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Feedback> feedbacks;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Favorite> favorites;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Share> shares;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<CartItem> cartItems;
	
	
}
