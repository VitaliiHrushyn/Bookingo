package com.droozhbooking.domain.hotelservices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.droozhbooking.domain.core.Booking;
import com.droozhbooking.domain.core.Room;

/** 
 * Класс содержит значимую информацию за определенный день для каждого номера отеля.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 * 
 * @ Список обьетов этого типа содержатся в Room. 
 *Информацией из этого класса удобно оперировать при администрировании отеля, 
 *доставая необходимую для каждого дня по определенному номеру в отеле.
 * 
 * 
 */

@Component
@Entity
@Table(name="ROOM_DAYS")
public class RoomDay implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = -3838365163761118900L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column (name="id")
	private Long id;
	
	/**
	 * Календарная дата
	 */
	@Column(name="date")
	private Date date;
	
	/**
	 * Объект Booking текущего дня - кем день забронирован. NULL - если день не забронирован.
	 * есть ответное поле.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="booking_id", nullable = true) // null if isn't booked
	private Booking booking; 
	
	/**
	 * Объект Room которому пренадлежит этот RoomDay.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="room_id", nullable = false)
	private Room room; 
 	
	/**
	 * Список питания на день. Названия (объекты) рационов в заказанном количестве. В привязке к комнате.
	 * Нет ответного поля, связи хранятся в отдельной таблице.
	 */
	@OneToMany(cascade = CascadeType.ALL) //  или OneToMany, все остальное остается тоже самое
	@JoinTable(
			name = "ROOM_DAY_DIETS", 
			joinColumns = { @JoinColumn(name = "room_day_id", referencedColumnName="id", nullable = false)},
			inverseJoinColumns = { @JoinColumn(name = "diet_id", referencedColumnName="id", nullable = true) })
	private List<Diet> Diets = new ArrayList<>(); // List - потому, что объкты Diet будут дублироваться в списке.
 	
	/**
	 * Список доп.услуг на день (экскурсии и прочее). В привязке к комнате.
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "ROOM_DAY_HOTEL_SERVICES", 
			joinColumns = { @JoinColumn(name = "room_day_id", referencedColumnName="id", nullable = false)},
			inverseJoinColumns = { @JoinColumn(name = "hotel_services_id", referencedColumnName="id", nullable = true) })
	private List<HotelService> hotelServices = new ArrayList<>();

	public RoomDay() {
		super();
	}

	public RoomDay(Date date, Booking booking, Room room, List<Diet> diets, List<HotelService> hotelServices) {
		super();
		this.date = date;
		this.booking = booking;
		this.room = room;
		Diets = diets;
		this.hotelServices = hotelServices;
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

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Diet> getDiets() {
		return Diets;
	}

	public void setDiets(List<Diet> diets) {
		Diets = diets;
	}

	public List<HotelService> getHotelServices() {
		return hotelServices;
	}

	public void setHotelServices(List<HotelService> hotelServices) {
		this.hotelServices = hotelServices;
	}

	@Override
	public String toString() {
		return "RoomDay [date=" + date + ", booking=" + booking + ", room=" + room + ", Diets=" + Diets
				+ ", hotelServices=" + hotelServices + "]";
	}

}
