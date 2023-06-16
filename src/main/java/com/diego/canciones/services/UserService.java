package com.diego.canciones.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.diego.canciones.models.User;
import com.diego.canciones.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User userByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	public User userRegistration(User u, BindingResult r) {
		if(userRepo.findByEmail(u.getEmail())==null) {
			String hashed = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
			u.setPassword(hashed);
			return userRepo.save(u);
		}else {
			r.rejectValue("email", "Matches", "El email ingresado ya existe");
			return null;
		}
	}
	
	public User findById(Long id) {
		return userRepo.findById(id).orElse(null);
	}
	
	public boolean authentication(String email, String password, BindingResult result) {
		User usuario = userRepo.findByEmail(email);
		if(usuario==null) {
			result.rejectValue("email", "Matches", "Email no valido");
			return false;
		}else {
			if(BCrypt.checkpw(password, usuario.getPassword())) {
				return true;
			}else {
				result.rejectValue("password", "Matches", "Password no valida");
				return false;
			}
		}
	}
	
	
	
	
}
