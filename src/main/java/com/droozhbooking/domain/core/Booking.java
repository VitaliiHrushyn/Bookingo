package com.droozhbooking.domain.core;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.droozhbooking.domain.bookingdetails.BookingStatus;
import com.droozhbooking.domain.hoteldetails.Testimonial;
import com.droozhbooking.domain.hotelservices.RoomDay;
import com.droozhbooking.domain.users.Guest;

/** 
 * Класс описывает объект БРОНИРОВАНИЯ номера.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 * 
 * @ подумать над механизмом добронирования как дополнительных дней прибывания в отеле, так и дозаказ доп.услуг, питания и т.п.
 */

@Component
@Entity
@Table(name="BOOKINGS")
public class Booking implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = -638391205109991396L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	/**
	 * Статус (состояние) объекта бронирования (создан, подтвержден, завершен, отменен)
	 * @see BookingStatus
	 */
	@Column(name="booking_status")
	private BookingStatus status;
	
	/**
	 * Кто забронировал.
	 * Есть ответное поле.
	 * @see Guest
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="guest_id")
	private Guest guest;
	
	/**
	 * Отель в котором забронировали комнату.
	 * Есть ответное поле.
	 * @see Hotel
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	
	/**
	 * Забронированная комната.
	 * НЕТ ответного поля.
	 * @see Room
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="room_id")
	private Room room;
	
	/**
	 * Список объктов RoomDay.
	 * это ответное поле.
	 * @see RoomDay
	 */
	@OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
	private List<RoomDay> bookedDates = new ArrayList<>();
	
	/**
	 * Комментарий постояльца для представителя отеля
	 */
	@Column(name="comment")
	private String comment;
	
	/**
	 * Общая цена за весь срок бронирования номера + все наперед забронированные доп.услуги, питание и т.п.
	 * Стоимость дозаказанных услуг в процессе пребывания в отеле сюда не включаются,
	 * так как это цена "путевки".
	 * МОЖЕТ ЭТО ПОЛЕ И НЕ НАДО - вычислять по запросу.
	 */
	@Column(name="booked_amount")
	private int bookedAmount;
	
	/**
	 * Общая цена за весь срок бронирования + все доп.услуги за весь срок бронирования.
	 * В это поле добавляются все стоимости всех заказов за срок пребывания в отеле про этому бронированию.
	 * МОЖЕТ ЭТО ПОЛЕ И НЕ НАДО - вычислять по запросу.
	 */
	@Column(name="total_amount")
	private int totalAmount;
	
	
	/**
	 * Поле с итоговым описанием бронирования, в String записывается вся значимая информация о бронировании,
	 * чтоб было удобно просмотреть всю информацию по бронированию без необходимости каждый раз вытягивать инфу со все полей.
	 * Из этого поля формируется письмо-подтверждние для постояльца и письмо-извещение для отеля.
	 * 
	 * Это поле может менятся постояльцем (меняется объект Booking и вносятся автоматом изменение в это поле)
	 * до момента подтверждения бронирования отелем (см. BookingStatus). После подтверждения бронирования отелем - 
	 * постояльцу приходит емейл с "путевкой" - описанием "тура". После этого поле не подлежит изменениям, 
	 * а все дозаказы постояльца уже в него не включаются, а приходят отдельными уведомлениями, но при этом 
	 * вносятся соответствующие изменения в необходимые объекты, например в RoomDay.
	 * @see BookingStatus
	 * @see RoomDay
	 */
	@Column(name="final_description")
	private String finalDescription;
	
	
	/**
	 * Отзыв на пребывание в отеле. Через 1-2 недели после окончания бронирования постояльцу приходит письмо с сылкой на страницу,
	 * где можно оставить отзыв. Отзывы могут оставить только постояльцы, которые бронировали комнату.
	 * НЕТ ответного поля.
	 * @see Testimonial
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="testimonial_id")
	private Testimonial testimonial;	
	
}
