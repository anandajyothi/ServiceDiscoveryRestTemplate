package com.setplex.ott.subscription.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

enum DeviceType {
	LAPTOP, MOBILE, TV;
}

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer subcription;
	@CreationTimestamp
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date created_date;
	@CreationTimestamp
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date updated_date;
	private String device_type;
	private String username;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSubcription(Integer subcription) {
		this.subcription = subcription;
	}

	public Integer getSubcription() {
		return this.subcription;
	}

	public void setCreatedDate(Date createdDate) {
		this.created_date = createdDate;
	}

	public Date getCreatedDate() {
		return this.created_date;
	}

	public Date getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}

}
