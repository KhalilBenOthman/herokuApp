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
public class UserDmdConges {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userName;
	
		//OneToOne with User
		@OneToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "userDmd",nullable = false)
		@JsonIgnore
		private User userDmd;
				
		//OneToMany with Conges	
		@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "userDmdConges" , orphanRemoval = true)
		private List<DmdConge> dmdConges = new ArrayList<>();
		
		
		
		
		
		
	

		public UserDmdConges() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public User getUserDmd() {
			return userDmd;
		}

		public void setUserDmd(User userDmd) {
			this.userDmd = userDmd;
		}

		public List<DmdConge> getDmdConges() {
			return dmdConges;
		}

		public void setDmdConges(List<DmdConge> dmdConges) {
			this.dmdConges = dmdConges;
		}

		

		
		
}
