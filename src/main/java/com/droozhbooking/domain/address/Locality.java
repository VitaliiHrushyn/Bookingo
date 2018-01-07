package com.droozhbooking.domain.address;

import java.io.Serializable;

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
 * Класс с названием населенного пункта.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 */

@Component
@Entity
@Table(name="LOCALITIES")
public class Locality implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = -1066217031570508694L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name="id")
	private Long id;

	/**
	 * Название населенного пункта
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * Название района, к которому относится этот населенный пункт.
	 * Есть ответное поле.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="district_id")
	private District district;

	/**
	 * Конструктор без аргументов
	 */
	public Locality() {
		super();
	}
	
	/**
	 * Конструктор с аргументом: название населенного пункта.
	 */
	public Locality(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Набор геттеров и сеттеров
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
	
	public District getDistrict() {
		return district;
	}

	/**
	 * Этот метод вызывается методом setLocality из класса Disrict.
	 * Класс District сеттером  addLocality() самостоятельно добавляет этот Locality в свою коллекцию <Locality>,
	 * а этому объекту через этот метод присваивает себя (объект District) в поле district этого объекта.
	 * @see District
	 * @param district
	 */
	protected void setDistrict(District district) {
		this.district = district;
	}
	
	/**
	 * Другие методы
	 */
	
	/**
	 * Добавляет этот населенный пункт в коллекцию нас.пунктор района, переданного в параметре.
	 * Методы района в свою очередь добавляют себя в переменную district этого класса.
	 * @param district
	 */
	public void addToDistrict(District district) {
		district.addLocalitiy(this);
	}
	
	@Override
	public String toString() {
		return "Locality: " + name;
	}	
	
}
