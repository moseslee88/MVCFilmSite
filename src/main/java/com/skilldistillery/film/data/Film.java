package com.skilldistillery.film.data;

import java.util.ArrayList;
import java.util.List;

public class Film {
	private String title;
	private int length;
	private String rating;
	private String description;
	private List<Actor> cast;
	
	
	public List<Actor> getCast() {
		return cast;
		//return new ArrayList<>(cast);   //Defensive copy that maximizes encapsulation
	}
	public void setCast(List<Actor> cast) {
		this.cast = cast;
		//this.cast= new ArrayList<>(cast);
	}
	public Film(String title,  String rating, String description, int length, List<Actor> cast) {
		this.title = title;
		this.length = length;
		this.rating = rating;
		this.description = description;
		this.cast=cast;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	
	//method to add Actor to the list
	public boolean addActor(Actor charlie)  {
		if (cast == null) {
			cast = new ArrayList<>();
		}
		return cast.add(charlie);
	}
	
	public boolean removeActor (Actor charlie)  {
		if (cast !=null) {
		return cast.remove(charlie);
	}
		else {
			return false;
		}
	}
	

}
