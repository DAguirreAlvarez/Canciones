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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="songs")
public class Song {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="El campo titulo no puede estar en blanco.")
	private String title;
	
	@NotBlank(message="El campo genero no puede estar en blanco.")
	private String genre;
	
	@NotBlank(message="Ingresa una letra de cancion.")
	@Size(min=5, message="Lyrics tiene un largo minimo de 5.")
	@Column(length=65535, columnDefinition="text")
	private String lyrics;
	
	private Integer colaboraciones;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="collaborators",
			joinColumns = @JoinColumn(name="song_id"),
			inverseJoinColumns= @JoinColumn(name="user_id"))
	private List<User> colaboradores;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User starter;
	
	public Song() {
		
	}
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
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

	public List<User> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<User> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public User getStarter() {
		return starter;
	}

	public void setStarter(User starter) {
		this.starter = starter;
	}

	public Integer getColaboraciones() {
		return colaboraciones;
	}

	public void setColaboraciones(Integer colaboraciones) {
		this.colaboraciones = colaboraciones;
	}
	
	
	
}
