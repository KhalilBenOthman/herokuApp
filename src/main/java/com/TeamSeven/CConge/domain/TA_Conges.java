package com.TeamSeven.CConge.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class TA_Conges {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "code est obligatoire.")
	@Size(min=4, max = 4, message="code taille doit etre 4 caractere")
	@Column(updatable = false, unique = true)
	private String code;
	@NotBlank(message = "libellé courte est obligatoire.")
	@Size(min=1, max = 50, message="libelle courte ne doit pas etre supreieur à 50 caractére")
	private String libelleCourt;
	@NotBlank(message = "libellé long est obligatoire.")
	@Size(min=1, max = 200, message="libelle courte ne doit pas etre supreieur à 200 caractére")
	private String libelleLong;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "TAConges")
	private RelatCongeType relatCongeType;
	
	
	
	public TA_Conges() {
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
	public RelatCongeType getRelatCongeType() {
		return relatCongeType;
	}
	public void setRelatCongeType(RelatCongeType relatCongeType) {
		this.relatCongeType = relatCongeType;
	}
	
}
