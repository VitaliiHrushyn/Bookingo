package com.droozhbooking.domain.core;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.droozhbooking.domain.hoteldetails.Facility;
import com.droozhbooking.domain.hotelservices.RoomDay;

/** 
 * Класс описывает объект КОМНАТА (номер) отеля.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 * 
 */

@Component
@Entity
@Table(name="ROOMS")
public class Room implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = -4487355915102372120L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	/**
	 * Номер комнаты в отеле
	 */
	@Column(name="number")
	private int number;
	
	/**
	 * количество комнат в многокомнатном номере или квартире или доме
	 */
	@Column(name="sub_rooms_quantity")
	private int subRoomsQuantity;
	
	/**
	 * вместительность номера (измеряется в человеках)
	 */
	@Column(name="roominess")
	private int roominess;
	
	/**
	 * этаж
	 */
	@Column(name="floor")
	private int floor;
	
	/**
	 * цена номера в сутки
	 */
	@Column(name="price_per_day")
	private BigDecimal pricePerDay;
	
	/**
	 * Описание номера
	 */
	@Column(name="description")
	private String description;
	
	/**
	 * Какому отелю пренадлежит комната.
	 * Есть ответноее поле.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	/**
	 * Список удобств в номере (кондиционер, минибар, гарячая вода в душе)
	 * @see Facility
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "ROOM_FACILITIES", 
			joinColumns = { @JoinColumn(name = "room_id", referencedColumnName="id") },
			inverseJoinColumns = { @JoinColumn(name = "facility_id", referencedColumnName="id") })
	private Set<Facility> facilities = new HashSet<>();
	
	/**
	 * Список ID, по которым на фронт-енде отобразятся фото номера
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name="ROOM_PICTURES",
			joinColumns = { @JoinColumn(name = "room_id", referencedColumnName="id") }, 
			inverseJoinColumns = { @JoinColumn(name = "picture_id", referencedColumnName="id") })
	private List<Picture> pictures = new ArrayList<>();
	
	/**
	 * Список объктов RoomDay, что-то типа календаря с информацией о номере по дням (на 365 дней вперед от текущей даты)
	 * Это ответное поле.
	 * @see RoomDay
	 * 
	 */
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private List<RoomDay> roomDays;

}
