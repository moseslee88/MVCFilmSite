package com.skilldistillery.film.data;

import java.util.List;

public interface FilmDao {
	String getFilmTitleById(int id);  
	List<Film> getFilmTitleByKeyword(String key);  
	//List<String> getFilmTitleByKeyword(String key);  
	//List<Film> getFilmTitleById(String newkey);  
}
