package com.pm.cpo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="cpo_login_table")
public class ChiefProductionOfficerDTO {
	@Id
	@GenericGenerator(name = "ref", strategy = "increment")
	@GeneratedValue(generator = "ref")
	private long id;
	@Column(name = "email",length = 100, unique = true)
	private String email;
	@Column(name = "password",length = 100)
	private String password;
	@Column(name = "status",length = 100)
	private String status;
	@Column(name = "active",length = 100)
	private String active;
	
	
	public ChiefProductionOfficerDTO() {
	
	}
	
	public ChiefProductionOfficerDTO(String email, String password, String status, String active) {
		super();
		this.email = email;
		this.password = password;
		this.status = status;
		this.active = active;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
		
	
}
