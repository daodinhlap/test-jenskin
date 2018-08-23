package com.lapdao.respone;

import java.util.Date;

public class IssuerRespone {
	private Long id;
	private String issuerCode;
	private String name;
	private Integer status;
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
	
}
