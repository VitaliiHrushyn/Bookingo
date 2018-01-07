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
 * Класс описывает каждую в отдельности услугу, предоставляемую отелем.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 * 
 * @ Список обьетов этого типа содержатся в Hotel (так как доп.сервисы ограничены возможностями отеля вцелом). 
 * Объкты доп.сервиса создаются один раз для каждой услуги.
 * Заказ услуг, их распределение по дням и т.п. осуществляется путем манипуляций со ссылками на конкретный экземпляр.
 * 
 * 
 */

@Component
@Entity
@Table(name="HOTEL_SERVICES")
public class HotelService implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = 7604841353750921802L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	/**
	 * Тип дополнительного сервиса, ограничен переснем реализаций класса HotelServiceType.
	 * По этому полю будет осуществлятся фильтрация. Клиенты сами будут придумывать 
	 * названия услуг, потому необходим универсвльный критерий фильтрации.
	 * @see HotelServiceType
	 */
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) // ?????????????
	@JoinColumn(name="hotel_service_type_id")
	private HotelServiceType type;
	
	/**
	 * Название - задается клиентом при создании экземпляра.
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * Описание услуги - задается клиентом при создании экземпляра.
	 */
	@Column(name="description")
	private String description;
	
	/**
	 * Цена услуги - задается клиентом при создании экземпляра.
	 */
	@Column(name="price")
	private BigDecimal price;
	
	/**
	 * флаг, является ли эта услуга стандартно входящей в стоимость номера (количество услуг
	 * соответсвует вместительности комнаты (если комната на трех человек - то в цену включено три массажа и т.п.).
	 * При отображении цены номера за сутки - цена такой стандартной услуги добавляется к цене номера ... ли нет - надо подумать.
	 * Этот флаг задается клиентом при создании экземпляра.
	 */
	@Column(name="is_standard")
	private boolean isStandard;
	
	/**
	 * Может ли услуга бронироваться заранее (TRUE)
	 * или заказывается и оплачивается только на месте (FALSE)
	 */
	@Column(name="is_prebookable")
	private boolean isPreBookable;

	public HotelService() {
		super();
	}

	public HotelService(HotelServiceType type, String name, String description, BigDecimal price, boolean isStandard,
			boolean isPreBookable) {
		super();
		this.type = type;
		this.name = name;
		this.description = description;
		this.price = price;
		this.isStandard = isStandard;
		this.isPreBookable = isPreBookable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HotelServiceType getType() {
		return type;
	}

	public void setType(HotelServiceType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "HotelService [type=" + type + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", isStandard=" + isStandard + ", isPreBookable=" + isPreBookable + "]";
	}
}
