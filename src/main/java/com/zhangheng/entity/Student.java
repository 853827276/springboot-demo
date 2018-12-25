package com.zhangheng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.zhangheng.common.BaseEntity;

@Entity
public class Student extends BaseEntity{

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	@Column(nullable = false,unique=true)
	private String name;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
