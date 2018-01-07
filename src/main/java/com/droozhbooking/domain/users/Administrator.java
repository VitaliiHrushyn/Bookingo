package com.droozhbooking.domain.users;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.droozhbooking.domain.core.Picture;


/** 
 * Класс описывает АДМИНИСТРАТОРА САЙТА
 * @version 1.1-snapshot
 * @author DroozhbakeeCoders 
 */

@Component
@Entity
@Table(name="ADMINISTRATORS")
public class Administrator implements Serializable {
	
	/**
	 * Серийный номер версии класса (сопоставляется при сериализации/десериализации)
	 */
	private static final long serialVersionUID = -5253048596089331838L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column (name="id")
	private Long id;
	
	/**
	 * Поле для записи роли для аккаунта администратора с разными уровнями доступа.
	 */
	@Column(name="administrator_role")
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

	public Administrator() {
		super();
	}

	public Administrator(OwnerRole role, String name, String nickName) {
		super();
		this.role = role;
		this.name = name;
		this.nickName = nickName;
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

	@Override
	public String toString() {
		return "Administrator [id=" + id + ", role=" + role + ", name=" + name + ", nickName=" + nickName + ", picture="
				+ picture + "]";
	}
		
}
