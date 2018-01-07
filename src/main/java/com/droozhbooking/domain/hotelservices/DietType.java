package com.droozhbooking.domain.hotelservices;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * Класс типов питания.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 * 
 * Используется как ограничение перечня допустимых значений типов питания.
 
 */

@Component
@Entity
@Table(name="DIET_TYPES")
public class DietType implements Serializable {

	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = 5721012029181430631L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;		
		
	/**
	 * Перечень создаваемых значений:
	 * RO, BB, HB, HBplus, FB, FBplus, AL, UAL, KITCHEN, LUNCH, DINNER. - 
	 * RO - room only: without feeding
	 * BB - bed and breakfast: only breakfast in the hotel
	 * HB - half board: breakfast + dinner
	 * HBplus - as previous + some drinks
	 * FB - full board: breakfast + lunch + dinner
	 * FBplus - as previous + some drinks
	 * AL - all inclusive
	 * UAL - ultra all inclusive
	 * 
	 * KITCHEN - allows customers to cook by they own in equipped kitchen
	 * LUNCH - order lunch separately 
	 * DINNER - order dinner separately
	 */	
	@Column(name="name")
	private String name;

	public DietType() {
		super();
	}

	public DietType(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DietType: " + name;
	}
	
}
