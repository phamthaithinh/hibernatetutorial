/*
 * devjav [http://devjav.com]
 * Copyright (C) 2014-2014 Pham Thai Thinh
 * Contact:phamthaithinh@gmail.com
 * 
 */
package com.devjav.tutorial.hibernate.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User persistence entity
 * 
 * @author Pham Thai Thinh
 * 
 */
@Entity(name = "users")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7292222119906752946L;
	private Long id;
	private String username;
	private String password;
	private Date crtDate;
	private Profile profile;
	private Set<LoginHistory> histories;
	private Set<Role> roles;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	@OneToOne(optional = false, mappedBy = "user",cascade=CascadeType.ALL)
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<LoginHistory> getHistories() {
		if (histories == null) {
			histories = new HashSet<LoginHistory>();
		}
		return histories;
	}

	public void setHistories(Set<LoginHistory> histories) {
		this.histories = histories;
	}

	@ManyToMany(mappedBy = "users", cascade = CascadeType.PERSIST)
	public Set<Role> getRoles() {
		if (roles == null) {
			roles = new HashSet<Role>();
		}
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
