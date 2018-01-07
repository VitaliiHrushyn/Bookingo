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
 * Класс типов РАСПОЛОЖЕНИЯ ОТЕЛЯ.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 */

@Component
@Entity
@Table(name="LOCATION_TYPES")
public class LocationType implements Serializable {

	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = 4850950243315042029L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;	
	
	/*
	 * Constructors
	 * *********************************************************
	 */
	
	/**
	 * Constructor with arguments
	 */
	
	public LocationType() {
		super();
	}
	
	/**
	 * Constructor with arguments
	 * @param name
	 */
	
	public LocationType(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Setters and Getters
	 */
	
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

	/**
	 * Methods
	 */
	
	@Override
	public String toString() {
		return "" + name;
	}
	
	
//	
//	/**
//	 * Отдых на море
//	 */
//	SEA, 
//	
//	/**
//	 * Отдых в горах
//	 */
//	MOUNTAINS, 
//	
//	/**
//	 * Местность с местными достопримечательностями.
//	 */
//	SIGHTSEEING,
//	
//	/**
//	 * "Зеленый" туризм.
//	 */
//	GREEN_TOURISM,
//	
//	/**
//	 * Просто населенный пункт. Например предложение оренды постоянного жилья без других особых признаков.
//	 */
//	ORDINARY

}
