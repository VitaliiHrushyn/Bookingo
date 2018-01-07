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
 * Класс с названием крупного региона страны (область, земля и т.п.).
 * Класс содержит список субрегионов (районы и т.п.)
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 */

@Component
@Entity
@Table(name="REGIONS")
public class Region implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = -8508393549049060498L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	/**
	 * Название области
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * Название страны, к которой относится регион.
	 * Есть ответное поле.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="country_id")
	private Country country;
	
	/**
	 * Список районов этой области.
	 * Это ответное поле.
	 */
	@OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
	private Set<District> districts = new HashSet<>();
	
	/**
	 * Конструкторы
	 * **************************************************************************
	 */
	/**
	 * Конструктор без аргументов
	 */
	public Region() {
		super();
	}
	
	/**
	 * Конструктор с аргументом: название населенного пункта.
	 */
	public Region(String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Набор геттеров и сеттеров
	 * *****************************************************************************
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

	public Country getCountry() {
		return country;
	}

	/**
	 * Этот метод вызывается методом setRegion из класса Country.
	 * Класс Country сеттером  addRegion() самостоятельно добавляет этот регион в свою коллекцию регионов,
	 * а этому региону через этот метод присваивает себя (объект Country) в поле country этого объекта.
	 * @see Country
	 * @param country
	 */
	protected void setCountry(Country country) {
		this.country = country;
	}

	public Set<District> getDistricts() {
		return districts;
	}
	
	/**
	 * Этот метод добавляет\заменяет коллекцию районов в объкт этого класса,
	 * @see District
	 * @param districts
	 */
	public void setDistricts(Set<District> districts) {
		this.districts = districts;
	}
	
	/**
	 * Другие методы
	 * ************************************************************************
	 */
	
	/**
	 * Этот метод добавляет район в коллекцию районов объкта этого класса,
	 * а также добавляет в объект этого района себя как регион этого района.
	 * @see District
	 * @param district
	 */
	public void addDistrict(District district) {
		this.districts.add(district);
		district.setRegion(this);
	}
	
	/**
	 * Добавляет этот Region в коллекцию областей страны, переданной в параметре.
	 * Методы страны (Country) в свою очередь добавляют себя в переменную country этого класса.
	 * @param country
	 */
	public void addToRegion(Country country) {
		country.addRegion(this);
	}
	
	@Override
	public String toString() {
		return "Region: " + name;
	}	
}
