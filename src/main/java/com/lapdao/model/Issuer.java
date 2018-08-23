package com.lapdao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "issuer")
public class Issuer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String issuerCode;
	private String name;
	private Integer status;
	@Column(name = "createdDate")
	private Date createdDate;
	private Date lastUpdate;
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getIssuerCode() {return issuerCode;}
	public void setIssuerCode(String issuerCode) {this.issuerCode = issuerCode;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public Date getCreatedDate() {return createdDate;}
	public void setCreatedDate(Date createDate) {this.createdDate = createDate;}
	
	public Date getLastUpdate() {return lastUpdate;}
	public void setLastUpdate(Date lastUpdate) {this.lastUpdate = lastUpdate;}
	
	
	public Integer getStatus() {return status;}
	public void setStatus(Integer status) {this.status = status;}
	
	@Transient
	  private String invokingQueue;
	  public String getInvokingQueue() { return invokingQueue; }
	  public void setInvokingQueue(String invokingQueue) { this.invokingQueue = invokingQueue; }
	
	
	@Override
	public String toString() {
		return "Issuer [id=" + id + ", issuerCode=" + issuerCode + ", name=" + name + ", createDate=" + createdDate
				+ ", lastUpdate=" + lastUpdate + "]";
	}
	
	public static class Status {
		public static final int ACTIVE = 1;
		public static final int DEACTIVE = 0;
	}
	
}
