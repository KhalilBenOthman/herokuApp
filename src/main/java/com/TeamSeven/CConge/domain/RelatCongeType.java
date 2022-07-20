package com.TeamSeven.CConge.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
public class RelatCongeType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String TACongeCode;
	
	//OneToOne with TA_Conges
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TAConges",nullable = false)
	@JsonIgnore
	private TA_Conges TAConges;
	
	//OneToMany with Conges
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "relatCongeType" , orphanRemoval = true)
	private List<Conges> conges = new ArrayList<>();
	
	
	public RelatCongeType() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTACongeCode() {
		return TACongeCode;
	}
	public void setTACongeCode(String tACongeCode) {
		TACongeCode = tACongeCode;
	}
	@JsonIgnore
	public TA_Conges getTAConges() {
		return TAConges;
	}
	@JsonIgnore
	public void setTAConges(TA_Conges tAConges) {
		TAConges = tAConges;
	}
	public List<Conges> getConges() {
		return conges;
	}
	public void setConges(List<Conges> conges) {
		this.conges = conges;
	}
	

	
}
