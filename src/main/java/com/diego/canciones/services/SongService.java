package com.diego.canciones.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diego.canciones.models.Song;
import com.diego.canciones.repositories.SongRepository;

@Service
public class SongService {

	
	@Autowired
	private SongRepository songRepo;
	
	public List<Song> listarCanciones(){
		return songRepo.findAll();
	}
	
	public Song crearCancion(Song s) {
		return songRepo.save(s);
	}
	
	public Song buscarCancion(Long id) {
		return songRepo.findById(id).orElse(null);
	}
	
	public void eliminarCancion(Song s) {
		songRepo.delete(s);
	}
	
	public Song cancionTitulo(String title) {
		return songRepo.findByTitle(title);
	}
}
