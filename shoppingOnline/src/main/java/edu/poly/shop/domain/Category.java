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
@Table(name = "Categories")
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(columnDefinition = "nvarchar(100) not null")
	private String name;
	
	@Column(nullable = false)
	private int type;
	
	@OneToMany(mappedBy = "category", cascade =  CascadeType.ALL)
	private Set<Product> products;
	
	@OneToMany(mappedBy = "category", cascade =  CascadeType.ALL)
	private Set<Promotion> promotions;
	
}
