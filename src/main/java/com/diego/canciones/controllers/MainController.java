package com.diego.canciones.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.diego.canciones.models.LoginUser;
import com.diego.canciones.models.Song;
import com.diego.canciones.models.User;
import com.diego.canciones.services.SongService;
import com.diego.canciones.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private SongService songServ;
	
	@GetMapping("/")
	public String logReg(Model modelo) {
		modelo.addAttribute("user", new User());
		modelo.addAttribute("login", new LoginUser());
		
		
		return "logreg.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User usuario, BindingResult result, Model modelo) {
		if(result.hasErrors()) {
			modelo.addAttribute("login", new LoginUser());
			
			return "logreg.jsp";
		}
		User usuarioRegistrado = userServ.userRegistration(usuario, result);
		modelo.addAttribute("login", new LoginUser());
		if(usuarioRegistrado!=null) {
			modelo.addAttribute("successRegister", "Gracias por registrarte, ahora procede a iniciar sesion.");
		}
		modelo.addAttribute("user", new User());
		return "logreg.jsp";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("login") LoginUser loginuser, BindingResult result, Model modelo, HttpSession sesion) {
		if(result.hasErrors()) {
			modelo.addAttribute("user", new User());
			return "logreg.jsp";
		}
		if(userServ.authentication(loginuser.getEmail(), loginuser.getPassword(), result)) {
			User usuarioLogeado = userServ.userByEmail(loginuser.getEmail());
			sesion.setAttribute("userID", usuarioLogeado.getId());
			return "redirect:/home";
		}else {
			modelo.addAttribute("user", new User());
			return "logreg.jsp"; 
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		sesion.setAttribute("userID", null);
		return "redirect:/";
	}
	
	@GetMapping("/home")
	public String dashboard(Model modelo, HttpSession sesion) {
		if(sesion.getAttribute("userID")== null) {
			return "redirect:/";
		}
		
		Long userId = (Long) sesion.getAttribute("userID");
		User usuarioRegistrado = userServ.findById(userId);
		
		modelo.addAttribute("user", usuarioRegistrado);
		modelo.addAttribute("canciones", songServ.listarCanciones());
		return "dashboard.jsp";
	}
	
	@GetMapping("/songs/new")
	public String songForm(Model modelo, HttpSession sesion) {
		if(sesion.getAttribute("userID")== null) {
			return "redirect:/";
		}
		Long userId = (Long) sesion.getAttribute("userID");
		User usuarioRegistrado = userServ.findById(userId);
		
		modelo.addAttribute("song", new Song());
		
		return "songForm.jsp";
	}
	
	@PostMapping("/songs/new")
	public String createSong(@Valid @ModelAttribute("song")Song song, BindingResult result, Model modelo, HttpSession sesion) {
		if(sesion.getAttribute("userID")== null) {
			return "redirect:/";
		}
		Long userId = (Long) sesion.getAttribute("userID");
		User usuarioRegistrado = userServ.findById(userId);
		if(result.hasErrors()) {
			return "songForm.jsp";
		}else {
			List<User> colaboradores = new ArrayList<>();
			colaboradores.add(usuarioRegistrado);
			song.setColaboradores(colaboradores);
			song.setStarter(usuarioRegistrado);
			song.setColaboraciones(1);
			if(songServ.cancionTitulo(song.getTitle())==null) {
				songServ.crearCancion(song);
				return "redirect:/home";				
			}else {
				result.rejectValue("title", "Exist", "El titulo ya existe, ingrese otro.");
				return "songForm.jsp";
			}
		}
	}
	
	@GetMapping("/songs/{id}")
	public String cancion(@PathVariable("id")Long id, Model modelo) {
		modelo.addAttribute("cancion", songServ.buscarCancion(id));
		return "song.jsp";
	}
	
	@GetMapping("/songs/{id}/edit")
	public String editFormSong(@PathVariable("id")Long id, Model modelo, HttpSession sesion) {
		if(sesion.getAttribute("userID")== null) {
			return "redirect:/";
		}
		Long userId = (Long) sesion.getAttribute("userID");
		User usuarioRegistrado = userServ.findById(userId);
		
		Song rola = songServ.buscarCancion(id);
		String liricas = rola.getLyrics();
		sesion.setAttribute("liricas", liricas);
		rola.setLyrics("");
		
		modelo.addAttribute("cancion", rola);
		modelo.addAttribute("liricas", liricas);
		return "songEdit.jsp";
	}
	
	@PutMapping("/songs/{id}/edit")
	public String songUpdate(@Valid @ModelAttribute("cancion")Song song, BindingResult result, 
			@PathVariable("id")Long id, Model modelo, HttpSession sesion) {
		if(result.hasErrors()) {
			return "redirect:/songs/"+id+"/edit";
		}
		if(sesion.getAttribute("userID")== null) {
			return "redirect:/";
		}
		
		Long userId = (Long) sesion.getAttribute("userID");
		User usuarioRegistrado = userServ.findById(userId);
		Song rola = songServ.buscarCancion(id);
		List<User> colab = rola.getColaboradores();
		String liricas =(String) sesion.getAttribute("liricas");
		
		
		if(rola.getColaboradores().contains(usuarioRegistrado)) {
			song.setColaboradores(colab);
			song.setColaboraciones(rola.getColaboraciones()+1);
			song.setStarter(rola.getStarter());
			song.setLyrics(liricas.concat(" "+song.getLyrics()));
			songServ.crearCancion(song);
			return "redirect:/home";
		}else {
			List<User> colaboradores = songServ.buscarCancion(id).getColaboradores();
			colaboradores.add(usuarioRegistrado);
			song.setColaboradores(colaboradores);
			song.setColaboraciones(rola.getColaboraciones()+1);
			song.setLyrics(liricas.concat(" "+song.getLyrics()));
			song.setStarter(rola.getStarter());
			songServ.crearCancion(song);
			return "redirect:/home";
		}
		
	}
	
	
	
	@DeleteMapping("/songs/{id}/delete")
	public String deleteSong(@PathVariable("id")Long id, HttpSession sesion) {
		if(sesion.getAttribute("userID")== null) {
			return "redirect:/";
		}
		
		Long userId = (Long) sesion.getAttribute("userID");
		User usuarioRegistrado = userServ.findById(userId);
		if(songServ.buscarCancion(id).getStarter().getId()==usuarioRegistrado.getId()) {
			songServ.eliminarCancion(songServ.buscarCancion(id));
			return "redirect:/home";			
		}
		return "redirect:/home";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
