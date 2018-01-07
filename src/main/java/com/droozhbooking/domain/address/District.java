package com.droozhbooking.domain.address;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/** 
 * Класс с названием района области.
 * Класс содержит список населенных пунктов этого района.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 */

@Component
@Entity
@Table(name="DISTRICTS")
public class District implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = 1392863712051049316L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column (name="id")
	private Long id;

	/**
	 * Название района
	 */
	@Column (name="name")
	private String name;
	
	/**
	 * Название региона, к которому относится район.
	 * Есть ответное поле.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="region_id")
	private Region region;
	
	/**
	 * Список населенных пунктов этого района.
	 * Это ответное поле.
	 */
	@OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
	private Set<Locality> localities = new HashSet<>();
	
	/**
	 * Конструкторы
	 * ********************************************************************
	 */
	
	/**
	 * Конструктор без аргументов
	 */
	public District() {
		super();
	}
	
	/**
	 * Конструктор с аргументом: название населенного пункта.
	 */
	public District(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Набор геттеров и сеттеров
	 * ***********************************************************************************
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

	
	public Region getRegion() {
		return region;
	}

	/**
	 * Этот метод вызывается методом setDistrict из класса Region.
	 * Класс Region сеттером  addDistrict() самостоятельно добавляет этот регион в свою коллекцию регионов,
	 * а этому региону через этот метод присваивает себя (объект Region) в поле region этого объекта.
	 * @see Region
	 * @param region
	 */
	protected void setRegion(Region region) {
		this.region = region;
	}

	public Set<Locality> getLocalities() {
		return localities;
	}

	/**
	 * Этот метод добавляет\заменяет коллекцию Locality в объкт этого класса,
	 * @see Locality
	 * @param localities
	 */
	public void setLocalities(Set<Locality> localities) {
		this.localities = localities;
	}
	
	/**
	 * Другие методы
	 * ********************************************************************************
	 */
	
	/**
	 * Этот метод добавляет объект Locality в коллекцию Locality объкта этого класса,
	 * а также добавляет в указанный  объект Locality себя в поле district.
	 * @see Locality
	 * @param locality
	 */
	public void addLocalitiy(Locality locality) {
		this.localities.add(locality);
		locality.setDistrict(this);
	}
	
	/**
	 * Добавляет этот District в коллекцию районов области, переданной в параметре.
	 * Методы области (Region) в свою очередь добавляют себя в переменную region этого класса.
	 * @param region
	 */
	public void addToRegion(Region region) {
		region.addDistrict(this);
	}
	
	@Override
	public String toString() {
		return "District: " + name;
	}	
	
}
