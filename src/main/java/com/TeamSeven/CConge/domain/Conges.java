package com.TeamSeven.CConge.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Conges {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable = false, unique = true)
	private String codeSequence;
	
	@NotBlank(message = "code est obligatoire.")
	@Column(updatable = false, unique = true)
	private String code;
	@NotBlank(message = "libellé courte est obligatoire.")
	@Size(min=1, max = 50, message="libelle courte ne doit pas etre supreieur à 50 caractére")
	private String libelleCourt;
	@NotBlank(message = "libellé long est obligatoire.")
	@Size(min=1, max = 200, message="libelle courte ne doit pas etre supreieur à 200 caractére")
	private String libelleLong;
	private Integer congeSolde;
	//ManyToOne with RelatCongeType
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="relatCongeType_id", updatable = false, nullable = false)
	@JsonIgnore
	private RelatCongeType relatCongeType;
	@Column(updatable = false)
	private String TACongeCode;

	public Conges() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelleCourt() {
		return libelleCourt;
	}

	public void setLibelleCourt(String libelleCourt) {
		this.libelleCourt = libelleCourt;
	}

	public String getLibelleLong() {
		return libelleLong;
	}

	public void setLibelleLong(String libelleLong) {
		this.libelleLong = libelleLong;
	}

	public Integer getCongeSolde() {
		return congeSolde;
	}

	public void setCongeSolde(Integer congeSolde) {
		this.congeSolde = congeSolde;
	}

	public String getTACongeCode() {
		return TACongeCode;
	}

	public void setTACongeCode(String tACongeCode) {
		TACongeCode = tACongeCode;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	@JsonIgnore
	public RelatCongeType getRelatCongeType() {
		return relatCongeType;
	}
	@JsonIgnore
	public void setRelatCongeType(RelatCongeType relatCongeType) {
		this.relatCongeType = relatCongeType;
	}

	public String getCodeSequence() {
		return codeSequence;
	}

	public void setCodeSequence(String codeSequence) {
		this.codeSequence = codeSequence;
	}
	
	
	
}
