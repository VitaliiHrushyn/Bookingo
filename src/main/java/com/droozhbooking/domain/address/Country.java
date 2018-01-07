package com.droozhbooking.domain.address;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import org.springframework.stereotype.Component;

/** 
 * Класс с названием страныю
 * Содержит список регионов (областей).
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 */

@Component
@Entity
@Table(name="COUNTRIES")
public class Country implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = -295339940683772379L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	/**
	 * Название страны
	 */
	@Column (name="name")
	private String name;
	
	/**
	 * Список областей этой страны.
	 * есть ответное поле.
	 */
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	private Set<Region> regions = new HashSet<>();
	
	/**
	 * Конструктор без аргументов
	 */
	public Country() {
		super();
	}
	
	/**
	 * Конструктор с аргументом: название населенного пункта.
	 */
	public Country(String name) {
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

	public void setName(String countryName) {
		this.name = countryName;
	}

	public Set<Region> getRegions() {
		return regions;
	}
	
	/**
	 * Этот метод добавляет\заменяет коллекцию регионов в объкт этого класса,
	 * @see Region
	 * @param regions
	 */
	public void setRegions(Set<Region> regions) {
		this.regions = regions;
	}

	
	/**
	 * Другие методы
	 */
	
	/**
	 * Этот метод добавляет регион в коллекцию регионов объкта этого класса,
	 * а также добавляет в объект этого региона себя как страну этого региона.
	 * @see Region
	 * @param region
	 */
	public void addRegion(Region region) {
		this.regions.add(region);
		region.setCountry(this);
	}
	
	@Override
	public String toString() {
		return "Country: " + name;
	}	
	
}
