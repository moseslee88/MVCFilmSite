package com.skilldistillery.film.data;

import java.util.List;


public interface FilmDao {
	String getFilmTitleById(int id);  
	List<Film> getFilmTitleByKeyword(String key);  
	public void addFilm(Film film);
	public int deleteFilm(int id);
	public boolean updateFilm(Film film);
	//List<String> getFilmTitleByKeyword(String key);  
	//List<Film> getFilmTitleById(String newkey); 
	List<Film> getAllFilms();
}
