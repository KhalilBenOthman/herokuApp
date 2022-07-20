package com.TeamSeven.CConge.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "users")
@Proxy(lazy = false)
public class User implements UserDetails{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email(message = "verifier l'email a une forme valide")
	@NotBlank(message = "l'email est obligatoire")
	@Column(unique = true)
	private String username;
	
	@NotBlank(message = "nom complet est obligatoire")
	private String fullName;
	
	@NotBlank(message = "mot de passe est obligatoire")
	private String password;
	
	private boolean isAdmin = false;
	private boolean isGestionaire = false;
	
	@Transient
	private String confirmPassword;
	
	private int solde;
	private int soldeRestant;
	
	//Donnees personnelle 
	private String cin;
	private String adresse;
	private String EtatCivile;
	private Date dateNaissance;
	private int age;
	private String natureLegaleContrat;
	private Date dateDebutContrat;
	private Date dateFinContrat;
	
	
	
	
	
	
	
	
	
	
	private Date create_At;
	private Date update_At;
	
	//OneToMany With CongeDemande
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "userDmd")
	private UserDmdConges userDmdConges;
	
	
	//OneToMany With CongeValid
	
	
	
	
	@PrePersist
	protected void onCreate() { this.create_At = new Date();}
	@PreUpdate
	protected void onUpdate() { this.update_At = new Date(); }
	public User() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Date getCreate_At() {
		return create_At;
	}
	public void setCreate_At(Date create_At) {
		this.create_At = create_At;
	}
	public Date getUpdate_At() {
		return update_At;
	}
	public void setUpdate_At(Date update_At) {
		this.update_At = update_At;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean getIsGestionaire() {
		return isGestionaire;
	}
	public void setIsGestionaire(Boolean isGestionaire) {
		this.isGestionaire = isGestionaire;
	}
	
	
	public int getSolde() {
		return solde;
	}
	public void setSolde(int solde) {
		this.solde = solde;
	}
	public int getSoldeRestant() {
		return soldeRestant;
	}
	public void setSoldeRestant(int soldeRestant) {
		this.soldeRestant = soldeRestant;
	}
	public UserDmdConges getUserDmdConges() {
		return userDmdConges;
	}
	public void setUserDmdConges(UserDmdConges userDmdConges) {
		this.userDmdConges = userDmdConges;
	}
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getEtatCivile() {
		return EtatCivile;
	}
	public void setEtatCivile(String etatCivile) {
		EtatCivile = etatCivile;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getNatureLegaleContrat() {
		return natureLegaleContrat;
	}
	public void setNatureLegaleContrat(String natureLegaleContrat) {
		this.natureLegaleContrat = natureLegaleContrat;
	}
	public Date getDateDebutContrat() {
		return dateDebutContrat;
	}
	public void setDateDebutContrat(Date dateDebutContrat) {
		this.dateDebutContrat = dateDebutContrat;
	}
	public Date getDateFinContrat() {
		return dateFinContrat;
	}
	public void setDateFinContrat(Date dateFinContrat) {
		this.dateFinContrat = dateFinContrat;
	}
	
	
	
	
	
	
}
