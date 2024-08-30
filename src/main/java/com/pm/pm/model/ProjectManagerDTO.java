package com.pm.pm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.pm.cpo.model.ChiefProductionOfficerDTO;
import com.pm.cpo.model.ProjectDTO;

@Entity
@Table(name = "project_manager")
public class ProjectManagerDTO {
	@Id
	@GenericGenerator(name = "ref", strategy = "increment")
	@GeneratedValue(generator = "ref")
	private long tid;
	
	@Column(length = 100)
	private String name;
	
	@Column(length = 100)
	private String email;
	
	@Column(length = 100)
	private String password;
	
	private long phone;
	
	@Column(length = 100)
	private String position;
	
	@Column(length = 20)
	private String status;
	
	@Lob
	@Column(name = "profile")
	private byte[] img;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cpo")
	private ChiefProductionOfficerDTO cpo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "project")
	private ProjectDTO project;

	public ProjectManagerDTO(long tid, String name, String email, String password, long phone, String position,
			String status, byte[] img, ChiefProductionOfficerDTO cpo, ProjectDTO project) {
		super();
		this.tid = tid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.position = position;
		this.status = status;
		this.img = img;
		this.cpo = cpo;
		this.project = project;
	}
	
	public ProjectManagerDTO() {
		
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public ProjectDTO getProject() {
		return project;
	}

	public void setProject(ProjectDTO project) {
		this.project = project;
	}
	
	
	
}
