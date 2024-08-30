package com.pm.cpo.model;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cpo_details_table")
public class CPODetailsDTO {
	@Id
	@GenericGenerator(name = "ref", strategy = "increment")
	@GeneratedValue(generator = "ref")
	private long id;
	
	@Column(name="name", length = 100)
	private String name;
	
	@Column(name="phone")
	private long phone;
	
	@Column(name="position")
	private String position;
	
	@Column(name="message", length = 500)
	private String message;
	
	@Lob
	@Column(name="profile")
	private byte[] img;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cpo")
	private ChiefProductionOfficerDTO cpo;
	
	

	public CPODetailsDTO(String name, String position, String message, byte[] img,
			ChiefProductionOfficerDTO cpo) {
		super();
		this.name = name;
		this.position = position;
		this.message = message;
		this.img = img;
		this.cpo = cpo;
	}
	
	public CPODetailsDTO() {
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public byte[] getImg() {
		return img;
	}


	public void setImg(byte[] img) {
		this.img = img;
	}


	public ChiefProductionOfficerDTO getCpo() {
		return cpo;
	}


	public void setCpo(ChiefProductionOfficerDTO cpo) {
		this.cpo = cpo;
	}
	
	
}
