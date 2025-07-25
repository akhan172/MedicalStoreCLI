package com.infosys.med_store.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Medicine {
	
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE)
	private int id;
	
	private String name;
	private int price;
	private int quantity;
	private String manf;
	private LocalDate expD;
	
	@OneToMany(mappedBy="med", cascade=CascadeType.PERSIST)
	private List<OrderItem> orderitems;
	
	
	
}
