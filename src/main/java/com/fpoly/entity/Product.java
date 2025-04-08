package com.fpoly.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Products")

public class Product  implements Serializable{
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	String image;
	Double price;
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	Date createDate = new Date();
	Boolean available;
	@ManyToOne
	@JoinColumn(name = "Categoryid")
	Category category;
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;	
}
