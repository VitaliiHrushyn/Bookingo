package com.droozhbooking.domain.hotelservices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/** 
 * Класс инкапсулирует значимую информацию об определенном дне в отеле, собирая информацию из объектов RoomDay.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 */

@Component
@Entity
@Table(name="HOTEL_DAYS")
public class HotelDay implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = -8099897219675383305L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column (name="id")
	private Long id;
	
	/**
	 * Календарная дата.
	 */
	@Column(name="name")
	private Date date;
	
	/**
	 * Список объктов RoomDay всех комнат отеля для этого же (календарного) дня.
	 * НЕТ ответного поля.
	 * @see RoomDay
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "HOTEL_DAY_ROOM_DAYS", 
			joinColumns = { @JoinColumn(name = "hotel_day_id", referencedColumnName="id") },
			inverseJoinColumns = { @JoinColumn(name = "room_day_id", referencedColumnName="id") })
	private List<RoomDay> roomDays = new ArrayList<>();

	public HotelDay() {
		super();
	}

	public HotelDay(Date date, List<RoomDay> roomDays) {
		super();
		this.date = date;
		this.roomDays = roomDays;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<RoomDay> getRoomDays() {
		return roomDays;
	}

	public void setRoomDays(List<RoomDay> roomDays) {
		this.roomDays = roomDays;
	}

	@Override
	public String toString() {
		return "HotelDay: date = " + date;
	}
}
