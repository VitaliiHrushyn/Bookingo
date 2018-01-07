package com.droozhbooking.domain.hoteldetails;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


/** 
 * Класс описывает отзыв постояльца об отеле.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders
 * 
 * @Подробнее Объкт этого класа располагается в классе Booking, а также список отзывов хранится в объкте Hotel. 
 * После отдыха постояльца в отеле, (после присвоения объекту Booking, согласно которого он находился в отеле, статуса FINISHED)  
 * через определенное время постоялец получит письмо с просьбой оценить (от 1 до 10) и при желании оставить письменный отзыв об отеле 
 * от своего имени или анонимно. 
 */

@Component
@Entity
@Table(name="TESTIMONIALS")
public class Testimonial implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = 3821285396194195484L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	/**
	 * ОЦЕНКА. Поле для оценке по 10-ти бальной шкале.
	 * (в сетере прописать ограничения устанавливаемых значений от 1 до 10)
	 */
	@Column(name="rate")
	private int rate;
	
	/**
	 * Поле с текстом отзыва.
	 */
	@Column(name="testimonial_text")
	private String testimonialText;
	
	/**
	 * Дата составления отзыва.
	 * @ Это объкт Отзыв связан с объектом Бронирование (Booking), но дата составления отзыва не следует 
	 * связывать с датами пребывания постояльца в отеле для того, чтобы предоставить возможность оставлять
	 * анонимные отзывы (по псевдониму) без возможности сопоставить отзывы с датами бронирования. 
	 */
	@Column(name="date")
	private Date date;
	
	/*
	 * Constructors
	 * *******************************************************************************
	 */
	
	/**
	 * Constructor without arguments
	 */

	public Testimonial() {
		super();
	}
	
	/**
	 * Constructor with arguments
	 * @param rate
	 * @param testimonialText
	 * @param date
	 */

	public Testimonial(int rate, String testimonialText, Date date) {
		super();
		this.rate = rate;
		this.testimonialText = testimonialText;
		this.date = date;
	}
	
	/*
	 * Getters and Setters
	 * *************************************************************
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getTestimonialText() {
		return testimonialText;
	}

	public void setTestimonialText(String testimonialText) {
		this.testimonialText = testimonialText;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	/*
	 * Methods
	 * ***************************************************************************************
	 */

	@Override
	public String toString() {
		return "Testimonial [rate=" + rate + ", testimonialText=" + testimonialText + ", date=" + date + "]";
	}
	
}
