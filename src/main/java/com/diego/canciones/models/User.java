package com.diego.canciones.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=3, max=30, message="El nombre tiene un largo minimo de 3 y maximo de 30")
	private String name;
	
	@Email
	@NotBlank(message="El campo email no debe estar vacio")
	private String email;
	
	@Size(min=8, max=64, message="La contrasenna tiene un largo minimo de 8")
	private String password;
	
	@Transient
	@Size(min=8, max=64, message="La contrasenna tiene un largo minimo de 8")
	private String passwordConfirmation;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	public User () {
		
	}
	
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="collaborators",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns= @JoinColumn(name="song_id"))
	private List<Song> colaboraciones;
	
	
	
	@OneToMany(mappedBy="starter", fetch=FetchType.LAZY)
	private List<Song> started;
	
	
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}


	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Song> getColaboraciones() {
		return colaboraciones;
	}

	public void setColaboraciones(List<Song> colaboraciones) {
		this.colaboraciones = colaboraciones;
	}

	public List<Song> getStarted() {
		return started;
	}

	public void setStarted(List<Song> started) {
		this.started = started;
	}
	
	
	
		
}
