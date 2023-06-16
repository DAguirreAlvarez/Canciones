package com.diego.canciones.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginUser {

	
	@Email
	@NotBlank(message="El campo email no debe estar vacio")
	private String email;
	
	@Size(min=8, max=64, message="La contrasenna tiene un largo minimo de 8")
	private String password;
	
	public LoginUser() {
		
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
	
	
}
