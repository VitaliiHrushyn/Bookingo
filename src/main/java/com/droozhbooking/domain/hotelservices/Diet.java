package com.droozhbooking.domain.hotelservices;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/** 
 * Класс описывает типы питания, предоставляемые отелем.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 * 
 * @ Список обьетов этого типа содержатся в Hotel (так как типы питания ограничены возможностями отеля вцелом). 
 * Объкты питания создаются один раз для каждой типа.
 * Экземпляр этого класса представляет собой тип предоставляемого питания на одну персону на один день.
 * Заказ питания, его распределение по дням и т.п. осуществляется путем манипуляций со ссылками на конкретный экземпляр
 * в RoomDay
 * 
 * @see RoomDay
 */

@Component
@Entity
@Table(name="DIETS")
public class Diet implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = 1953820136193269680L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	/**
	 * По этому полю будет осуществлятся фильтрация оно же выступит названием для показа постояльцу.
	 * Один объект Diet - один DietType
	 * @see DietType
	 */	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="diet_type_id")
	private DietType dietTypeAndName;
	
	/**
	 * Описание питания - задается отелем при создании экземпляра.
	 */
	@Column(name="description")
	private String description;
	
	/**
	 * Цена питания - задается отелем при создании экземпляра - стоимость за одну персону за один день!
	 */
	@Column(name="price")
	private BigDecimal price;
	
	/**
	 * флаг, является ли это питание стандартно входящей в стоимость номера (количество услуг
	 * соответсвует вместительности комнаты (если комната на трех человек - то в цену включено три завтрака и т.п).
	 * При отображении цены номера за сутки - цена такой стандартного питания добавляется к цене номера... 
	 * или его цену установить "0" - надо подумать.
	 * Этот флаг задается отелем при создании экземпляра.
	 */
	@Column(name="is_standard")
	private boolean isStandard;
	
	/**
	 * Может ли питание бронироваться заранее (TRUE)
	 * или заказывается и оплачивается только на месте (FALSE)
	 */
	@Column(name="is_prebookable")
	private boolean isPreBookable;

	/**
	 * Constructor without agrument
	 */
	public Diet() {
		super();
	}
	
	/**
	 * Constructor with arguments
	 * @param dietTypeAndName
	 * @param description
	 * @param price
	 * @param isStandard
	 * @param isPreBookable
	 */

	public Diet(DietType dietTypeAndName, String description, BigDecimal price, boolean isStandard,
			boolean isPreBookable) {
		super();
		this.dietTypeAndName = dietTypeAndName;
		this.description = description;
		this.price = price;
		this.isStandard = isStandard;
		this.isPreBookable = isPreBookable;
	}
	
	/*
	 * Getters ans Setters
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DietType getDietTypeAndName() {
		return dietTypeAndName;
	}

	public void setDietTypeAndName(DietType dietTypeAndName) {
		this.dietTypeAndName = dietTypeAndName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isStandard() {
		return isStandard;
	}

	public void setStandard(boolean isStandard) {
		this.isStandard = isStandard;
	}

	public boolean isPreBookable() {
		return isPreBookable;
	}

	public void setPreBookable(boolean isPreBookable) {
		this.isPreBookable = isPreBookable;
	}
	
	/*
	 * Methods
	 */

	@Override
	public String toString() {
		return "Diet: Type = " + dietTypeAndName + ", description = " + description + ", price = " + price + ".";
	}
	
}
