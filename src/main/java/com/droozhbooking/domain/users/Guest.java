package com.droozhbooking.domain.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.droozhbooking.domain.core.Booking;
import com.droozhbooking.domain.core.Hotel;
import com.droozhbooking.domain.core.Picture;

/** 
 * Класс описывает ПОСТОЯЛЬЦА отеля (хостела и т.п.)
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders 
 */

@Component
@Entity
@Table(name="GUESTS")
public class Guest implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = -6744554585446662423L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column (name="id")
	private Long id;
	
	/**
	 * Поле для записи имени и/или фамилии и/или отчества через пробел.
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * Поле для записи псевдонима.
	 */
	@Column(name="nick_name")
	private String nickName;
	
	/**
	 * Поле содержит ИД картинки из БД, соответствующей постояльцу.
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="picture_id")
	private Picture picture;
	
	/**
	 * Поле содержит список отелей, отмеченных постояльцем как избранные (любимые отели).
	 * нет ответного поля.
	 * @see Hotel
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "GUEST_FAVOURITE_HOTELS", 
			joinColumns = { @JoinColumn(name = "guest_id", referencedColumnName="id") },
			inverseJoinColumns = { @JoinColumn(name = "hotel_id", referencedColumnName="id") })
	private List<Hotel> favouriteHotels = new ArrayList<>();
	
	/**
	 * Поле содержит список всех бронирований постояльца (текущих, архивных, отмененных).
	 * Это ответное поле.
	 * @see Booking
	 */
	@OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
	private List<Booking> bookings;

	public Guest() {
		super();
	}

	public Guest(String name, String nickName, Picture picture) {
		super();
		this.name = name;
		this.nickName = nickName;
		this.picture = picture;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public List<Hotel> getFavouriteHotels() {
		return favouriteHotels;
	}

	public void setFavouriteHotels(List<Hotel> favouriteHotels) {
		this.favouriteHotels = favouriteHotels;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "Guest [id=" + id + ", name=" + name + ", nickName=" + nickName + ", picture=" + picture
				+ ", favouriteHotels=" + favouriteHotels + ", bookings=" + bookings + "]";
	}	

}
