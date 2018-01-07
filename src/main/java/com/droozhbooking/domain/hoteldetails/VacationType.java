package com.droozhbooking.domain.hoteldetails;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/** 
 * Класс описывает категории предоставляемого отелем отдыха.
 * Можно для одного объекта выбрать несколько вариантов.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 */

@Component
@Entity
@Table(name="VACATION_TYPES")
public class VacationType implements Serializable {

	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = 4574019952593342071L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;

	/**
	 * Constructors without argument
	 */
	
	public VacationType() {
		super();
	}
	
	/**
	 * Constructors with argument
	 */

	public VacationType(String name) {
		super();
		this.name = name;
	}
	
	/*
	 * Getters and Setters
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamee() {
		return name;
	}

	public void setNamee(String name) {
		this.name = name;
	}
	
	/*
	 *Methods
	 */

	@Override
	public String toString() {
		return ""+name;
	}
	
		
	
//	/**
//	 * Семейный отдых с детьми
//	 */
//	FAMILY,
//	
//	/**
//	 * Романтический отдых для пары
//	 */
//	COUPLE,
//	
//	/**
//	 * Молодежный отдых
//	 */
//	YOUTH,
//	
//	/**
//	 * Отдых для детей
//	 */
//	KIDS,
//	
//	/**
//	 * Активный отдых (походы, рафтинг и прочее)
//	 */
//	ACTIVE,
//	
//	/**
//	 * Экстремальный отдых
//	 */
//	EXTRIME

}
