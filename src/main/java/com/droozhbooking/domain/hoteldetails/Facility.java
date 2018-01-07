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
 * Класс типов удобств ОТЕЛЯ (Hotel) и/или КОМНАТЫ (Room). 
 * Удобства отеля вцелом формируются из (неповторяющегося) множества удобств его комнат, а
 * также удобств, которые могут относится только к отелю вцелом (бассейн, кафе, бильярд).
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 */

@Component
@Entity
@Table(name="FACILITIES")
public class Facility implements Serializable {

	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = 3099854524287606562L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;

	/**
	 * Constructors
	 * ***********************************************************************8
	 */
	
	/**
	 * Конструктор без аргументов
	 */
	public Facility() {
		super();
	}
	
	/**
	 * Конструктор с аргументами
	 */
	public Facility(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Setters and Getters
	 * **********************************************************
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
	 * *********************************************************
	 */
	
	@Override
	public String toString() {
		return "" + name;
	}
	
	

	
	
//	SEEVIEW, 
//	MOUNTAINVIEW, 
//	CONDITIONER, 
//	MINIBAR, 
//	PARKING, 
//	POOL, 
//	GARDEN, 
//	CAFE, 
//	RESTAURANT, 
//	BILLIARDS, 
//	PLAYGROUND

}
