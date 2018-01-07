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
 * Класс описывает СОБСТВЕННИКА отеля и его доверенных сотрудников.
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders 
 */

@Component
@Entity
@Table(name="OWNERS")
public class Owner implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = 686430026201831414L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column (name="id")
	private Long id;
	
	/**
	 * Поле для записи роли для аккаунта собственника (администратор отеля, повар, уборщик, организатор экскурсий
	 * и т.п. - для каждого свой набор доступов и статистики).
	 */
	@Column(name="owner_role")
	private OwnerRole role;
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
	 * Поле содержит список всех отелей, относящихся к собственнику.
	 * нет ответного поля.
	 * @see Booking
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "OWNERS_HOTELS", 
			joinColumns = { @JoinColumn(name = "owner_id", referencedColumnName="id") },
			inverseJoinColumns = { @JoinColumn(name = "hotel_id", referencedColumnName="id") })
	private List<Hotel> ownedHotels = new ArrayList<>();

	/**
	 * 
	 */
	public Owner() {
		super();
	}

	public Owner(OwnerRole role, String name, String nickName, Picture picture) {
		super();
		this.role = role;
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

	public OwnerRole getRole() {
		return role;
	}

	public void setRole(OwnerRole role) {
		this.role = role;
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

	public List<Hotel> getOwnedHotels() {
		return ownedHotels;
	}

	public void setOwnedHotels(List<Hotel> ownedHotels) {
		this.ownedHotels = ownedHotels;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", role=" + role + ", name=" + name + ", nickName=" + nickName + ", picture="
				+ picture + ", ownedHotels=" + ownedHotels + "]";
	}	

}
