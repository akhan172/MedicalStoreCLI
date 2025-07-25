package com.infosys.med_store.entity;


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "Orders")
public class Order {
	public enum Status{
		PLACED,
		SHIPPED,
		CANCELLED
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private LocalDate orderDate;
	private int amount;

	private Status status;
	
	@OneToMany(mappedBy="order", cascade=CascadeType.ALL)
	private List<OrderItem> items;
	
	@ManyToOne
	private Customer customer;
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", amount=" + amount + ", status=" + status
				+ "]";
	}
}
