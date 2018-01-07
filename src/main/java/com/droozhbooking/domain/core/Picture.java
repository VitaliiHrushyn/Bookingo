package com.droozhbooking.domain.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/** 
 * Класс создан как заглушка для hibernate - потом придумать как правильно сохранять картинки.
 * @version 1.0-snapshot
 * @author DroozhbakeeCoders
 * 
 */

@Component
@Entity
@Table(name="PICTURES")
public class Picture implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = 1629469790553351417L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;

}
