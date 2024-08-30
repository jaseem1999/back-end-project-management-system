package com.pm.cpo.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "project_table")
public class ProjectDTO {
	@Id
	@GenericGenerator(name = "ref", strategy = "increment")
	@GeneratedValue(generator = "ref")
	private long tid;
	
	@Column(length = 100)
	private String pname;
	
	@Column(length = 500)
	private String description;
	private Date startdate;
	private Date enddate;
	
	@Lob
	private byte[] img;
	
	@Lob
	private byte[] pdf;
	
	@Column(length = 100)
	private String client;
	
	@Column(length = 30)
	private String status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cpo")
	private ChiefProductionOfficerDTO cpo;

	public ProjectDTO(long tid, String pname, String description, Date startdate, Date enddate, byte[] img, byte[] pdf,
			String client, String status, ChiefProductionOfficerDTO cpo) {
		super();
		this.tid = tid;
		this.pname = pname;
		this.description = description;
		this.startdate = startdate;
		this.enddate = enddate;
		this.img = img;
		this.pdf = pdf;
		this.client = client;
		this.status = status;
		this.cpo = cpo;
	}
	
	public ProjectDTO() {
		
	}

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public byte[] getPdf() {
		return pdf;
	}

	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ChiefProductionOfficerDTO getCpo() {
		return cpo;
	}

	public void setCpo(ChiefProductionOfficerDTO cpo) {
		this.cpo = cpo;
	}
	
	
}
