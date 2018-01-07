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
 * Класс типов дополнительных сервисов, предоставляемых отелем.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 * 
 * @ Дополнительный сервисы AdditionalService отели создают самостоятельно.
 * Название типа дополнительного сервиса ограничивается реализациями этого класса для возможности фильтрации.
 *
 */

@Component
@Entity
@Table(name="HOTEL_SERVICE_TYPES")
public class HotelServiceType implements Serializable {

	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = -4453517301768055593L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	/**
	 * Трансфер от вокзала до отеля.
	 */
//	TRANSFER,
	
	/**
	 * Экскурсии 
	 */
//	EXCURSION,
	
	/**
	 * Развлечение/досуг
	 */
//	ENTERTAINMENT,
	
	/**
	 * Пакетное предложение: вместе дешевле
	 */
//	PACKAGE_OFFER
	
	@Column(name="name")
	private String name;

	public HotelServiceType() {
		super();
	}

	public HotelServiceType(String name) {
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
		return "HotelServiceType [name=" + name + "]";
	}	

}
