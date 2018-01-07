package com.droozhbooking.domain.core;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.droozhbooking.domain.address.Address;
import com.droozhbooking.domain.hoteldetails.*;
import com.droozhbooking.domain.hotelservices.*;

/** 
 * Класс описывает объект размещения, далее в описании класса для упрощения называемый ОТЕЛЬ, 
 * (к объктам размещения также относятся хостел, квартира, база отдыха, пансионат, дом и т.п. где можно арендовать номер, комнату, койку или целиком объект)
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 */

@Component
@Entity
@Table(name="HOTELS")
public class Hotel implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = -7895309246703529229L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	/**
	 * Поле адреса отеля.
	 * Одному отелю - один адрес.
	 * @see Address 
	 */
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	/**
	 * Поле с рейтингом отеля. Формируется как среднее арифметическое от указанных в отзывах постояльцев оценок.
	 * В классе Testimonial есть соответствующее поле.
	 * @see Testimonial  
	 */
	@Column(name="rate")
	private int rate;
	
	/**
	 * Поле с указанием количества звезд у отелей, для которых это предусмотрено.
	 * "0" - если отель не подлежит оценке по такому показателю. 
	 */
	@Column(name="stars_quantity")
	private int starsQuantity;
	
	/**
	 * Поле со свободным текстовым описанием отеля.
	 */
	@Column(name="description")
	private String description;
	
	/**
	 * Поле с указанием типа объекта, предоставляещего услуги размещения (отель, хостел, кемпинг, квартира и т.п.).
	 * @see HotelType
	 */
	@Column(name="hotel_type")
	private HotelType hotelType;
	
	/**
	 * Поле с коллекцией типов пребывания, предоставляемого отелем 
	 * (семейный с детьми, романтический для пары, молодежный и т.п.).
	 * НЕТ ответного поля, связи в доп.таблице.
	 * @see VacationType
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "HOTEL_VACATION_TYPES", 
			joinColumns = { @JoinColumn(name = "hotel_id", referencedColumnName="id") },
			inverseJoinColumns = { @JoinColumn(name = "vacation_type_id", referencedColumnName="id") })
	private Set<VacationType> vacationTypes = new HashSet<>();
	
	/**
	 * Поле со списком типов локации отдыха 
	 * (горы, море, осмотр достопримечательностей  и т.п., а также их комбинации)
	 * НЕТ ответного поля, связи в доп.таблице.
	 * @see LocationType
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "HOTEL_LOCATION_TYPES", 
			joinColumns = { @JoinColumn(name = "hotel_id", referencedColumnName="id") },
			inverseJoinColumns = { @JoinColumn(name = "location_type_id", referencedColumnName="id") })
	private Set<LocationType> LocationTypes = new HashSet<>();
	
	/**
	 * Поле с указанием типа продолжительности размещения.
	 * Это поле заполняется значением ENUM.
	 * @see AccomodationTermType
	 */
	@Column(name="accomodation_term_type") // ЭТО ПОЛЕ НЕ ИМЕЕТ СВЯЗИ С ДРУГИМИ ТАБЛИЦАМИ.
	private AccomodationTerm accomodationTermType;
	
	/**
	 * Поле для списка отзывов о отеле.
	 * НЕТ ответного поля, все связив в доп.таблице.
	 * @see Testimonial
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "HOTEL_TESTIMONIALS", 
			joinColumns = { @JoinColumn(name = "hotel_id", referencedColumnName="id") },
			inverseJoinColumns = { @JoinColumn(name = "testimonial_id", referencedColumnName="id") })
	private List<Testimonial> testimonials = new ArrayList<>();
	
	/**
	 * Поле для коллекции Booking этого Hotel.
	 * Это ответное поле.
	 * @see Booking
	 */
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private Set<Booking> bookings = new HashSet<>();
	
	/**
	 * Поле со списком удобств, предоставляемых отелем (душ в номере, кондиционер, бассейн и т.п.)
	 * НЕТ ответного поля, связи в доп.таблице.
	 * @see Facility
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "HOTEL_FACILITIES", 
			joinColumns = { @JoinColumn(name = "hotel_id", referencedColumnName="id") },
			inverseJoinColumns = { @JoinColumn(name = "facility_id", referencedColumnName="id") })
	private Set<Facility> facilities = new HashSet<>();
	
	/**
	 * Поле со перечнем типов предосталяемого отелем питания (RO, HB AL и т.п.)
	 * Заказ питания - см. следующее поле.
	 * Наличие этой коллекции для удобства фильтрации отелей 
	 * - чтоб не вытягивать из базы коллекцию созданных администратором отеля реализаций Diet и из них получать тип питания.
	 * НЕТ ответного поля, связи в доп.таблице.
	 * @see Diet
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "HOTEL_DIET_TYPE", 
			joinColumns = { @JoinColumn(name = "hotel_id", referencedColumnName="id") },
			inverseJoinColumns = { @JoinColumn(name = "diet_type_id", referencedColumnName="id") })
	private Set<DietType> dietTypes = new HashSet<>();
	
	/**
	 * Поле со перечнем реализаций предосталяемого отелем питания (RO, HB AL и т.п.)
	 * Заказ питания - добавление объекта Diet в соответсвующую коллекцию объекта RoomDay.
	 * НЕТ ответного поля, связи в доп.таблице.
	 * @see Diet
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "HOTEL_DIET", 
			joinColumns = { @JoinColumn(name = "hotel_id", referencedColumnName="id") },
			inverseJoinColumns = { @JoinColumn(name = "diet_id", referencedColumnName="id") })
	private Set<Diet> diets = new HashSet<>();
	
	/**
	 * Поле с перечнем типов предосталяемых отелем услуг (экскурсии, бильярд и прочее)
	 * Заказ услуг - добавление объекта HotelService в соответсвующую коллекцию объекта RoomDay.
	 * Это поле для удобства фильтрации, см предыдущие поля.
	 * @see HotelService
	 * @see HotelServiceType
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "HOTEL_HOTEL_SERVICE_TYPE", 
			joinColumns = { @JoinColumn(name = "hotel_id", referencedColumnName="id") },
			inverseJoinColumns = { @JoinColumn(name = "hotel_service_type_id", referencedColumnName="id") })
	private Set<HotelServiceType> hotelServiceTypes = new HashSet<>();
	
	/**
	 * Поле с набором услуг отеля (объктов класса услуг). Эти объекты созданы клиентами,
	 * согласно своих индивидуальных характеристик.
	 * @see HotelService
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "HOTEL_HOTEL_SERVICE", 
			joinColumns = { @JoinColumn(name = "hotel_id", referencedColumnName="id") },
			inverseJoinColumns = { @JoinColumn(name = "hotel_service_id", referencedColumnName="id") })
	private Set<HotelService> hotelServices = new HashSet<>();
	
	/**
	 * Поле с указанием ИД картинок из БД с изображением отеля
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name="HOTEL_PICTURES",
			joinColumns = { @JoinColumn(name = "hotel_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "picture_id")})
	private List<Picture> pictures = new ArrayList<>();
	
	/**
	 * Поле с коллекцией комнат отеля.
	 * Это ответное поле.
	 * @see Room
	 */
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private Set<Room> rooms = new HashSet<>();
	
}
