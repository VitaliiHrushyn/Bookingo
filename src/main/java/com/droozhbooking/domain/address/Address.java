package com.droozhbooking.domain.address;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import com.droozhbooking.domain.core.Hotel;

/** 
 * Класс инкапсулирует информацию об адресе объекта.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 * @ В случае отсутсвия у объекта какого-либо предусмотренного в этом классе элемента адреса - 
 * соответствующему полю присваивается значение NULL.
 */

@Component
@Entity
@Table(name="ADDRESSES")
public class Address implements Serializable {

	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = -6792989606402096750L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	/**
	 * Поле для записи отеля, которому пренадлежит адресс, так как для каждого объекта отеля
	 * будет создаваться свой объект адреса.
	 * @see Hotel
	 * Это ответное поле для Hotel.
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy="address")
	private Hotel hotel;

	/**
	 * Поле для записи страны из класса Country
	 * @see Country
	 * Ответного поля в классе Country НЕТ. 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="country_id")
	private Country country;

	/**
	 * Поле для записи названия крупного региона: области, земли и т.п.
	 * @ Проработать возможность добавления в список названий регионов клиентами при регистрации отеля.
	 * @see Region
	 * Ответного поля нет.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="region_id", nullable = true)
	private Region region;

	/**
	 * Поле для записи названия местной территори(субрегиона): района, района в городе и т.п.
	 * @ Проработать возможность добавления в список названий районов клиентами при регистрации отеля.
	 * @see District
	 * Ответного поля нет.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="district_id", nullable = true)
	private District district;

	/**
	 * Поле для записи населенного пункта.
	 * @ Проработать возможность добавления в список названий населенных пунктов клиентами при регистрации отеля.
	 * @see Locality
	 * Ответного поля нет.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="locality_id")
	private Locality locality;

	/**
	 * Поле с названием улицы
	 */
	@Column(name="street")
	private String street;

	/**
	 * Поле с номером дома (в строковом представлении)
	 */
	@Column(name="building")
	private String building;

	/**
	 * Поле с номером комнаты, квартиры и т.п. ЕСЛИ эта квартира/комната и есть объект,
	 * подразумеваемый под понятием Отель этого приложения. Если отель имеет несколько комнат - 
	 * их номера указываются в соответсвующем поле объекта Room, а этому полю присваеваится NULL.
	 */
	@Column(name="apartment", nullable = true)
	private String apartment;

	/**
	 * Поле для почтового индекса (в строковом представлении)
	 */
	@Column(name="zip_code", nullable = true)
	private String ZIPcode;

	/**
	 * Поле для номера телефона (в строковом представлении)
	 */
	@Column(name="phone_number", nullable = true)
	private String phoneNumber;

	/**
	 * Поле для email.
	 */
	@Column(name="email")
	private String email;

	
	/**
	 * Конструктор без аргументов
	 */
	public Address() {
		super();
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


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


	public Region getRegion() {
		return region;
	}


	public void setRegion(Region region) {
		this.region = region;
	}


	public District getDistrict() {
		return district;
	}


	public void setDistrict(District district) {
		this.district = district;
	}


	public Locality getLocality() {
		return locality;
	}


	/**
	 * Этого метода достаточно, чтоб внести в адрес населенный пункт, район, область и страну.
	 * @param locality
	 */
	public void setLocality(Locality locality) {
		this.locality = locality;
		this.district = this.locality.getDistrict();
		this.region = this.district.getRegion();
		this.country = this.region.getCountry();
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getBuilding() {
		return building;
	}


	public void setBuilding(String building) {
		this.building = building;
	}


	public String getApartment() {
		return apartment;
	}


	public void setApartment(String apartment) {
		this.apartment = apartment;
	}


	public String getZIPcode() {
		return ZIPcode;
	}


	public void setZIPcode(String ZIPcode) {
		this.ZIPcode = ZIPcode;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Другие методы
	 */
	
	@Override
	public String toString() {
		return "Address: "
				+ "country: " + country 
				+ ", region: " + region 
				+ ", district: " + district 
				+ ", locality: " + locality 
				+ ", street: " + street 
				+ ", building: " + building 
				+ ", apartment: " + apartment 
				+ ", ZIP code: " + ZIPcode 
				+ ", phone: " + phoneNumber 
				+ ", email: " + email + ".";
	}	
	
}
