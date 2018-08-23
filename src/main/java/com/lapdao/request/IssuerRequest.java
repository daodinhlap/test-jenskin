package com.lapdao.request;

import java.util.Date;

public class IssuerRequest {
	private Long id;
	private String issuerCode;
	private String name;
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
	@Override
	public String toString() {
		return "Issuer [id=" + id + ", issuerCode=" + issuerCode + ", name=" + name + ", createDate=" + createdDate
				+ ", lastUpdate=" + lastUpdate + "]";
	} 
	
	
}
