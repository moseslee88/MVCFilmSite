package com.skilldistillery.film.data;

import java.util.ArrayList;
import java.util.List;

public class Film {
	private int id;


	private String title;
	private String description;
	private int releaseyear;
	private int rentalduration;
	private double rentalrate;
	private int length;
	private double replacementcost;
    private String rating; // String('G','PG','PG13','R','NC17')
	private String specialfeatures;
	private List<Actor> cast;
	
	public Film() {
		
	}

	public Film(String title, String rating, String description, int length, List<Actor> cast) {
		this.title = title;
		this.rating = rating;
		this.description = description;
		this.length = length;
		this.cast = cast;
	}

	
	//special constructor for all 9 parameters
	public Film(String title, String description, int releaseyear, int rentalduration, double rentalrate,
			int length, double replacementcost, String rating, String specialfeatures) {
		this.title = title;
		this.description = description;
		this.releaseyear = releaseyear;
		this.rentalduration = rentalduration;
		this.rentalrate = rentalrate;
		this.length = length;
		this.replacementcost = replacementcost;
		this.rating = rating;
		this.specialfeatures = specialfeatures;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}   

	public int getReleaseyear() {
		return releaseyear;
	}

	public void setReleaseyear(int releaseyear) {
		this.releaseyear = releaseyear;
	}

	public int getRentalduration() {
		return rentalduration;
	}

	public void setRentalduration(int rentalduration) {
		this.rentalduration = rentalduration;
	}

	public double getRentalrate() {
		return rentalrate;
	}

	public void setRentalrate(double rentalrate) {
		this.rentalrate = rentalrate;
	}

	public String getSpecialfeatures() {
		return specialfeatures;
	}

	public void setSpecialfeatures(String specialfeatures) {
		this.specialfeatures = specialfeatures;
	}

	public List<Actor> getCast() {
		return cast;
		// return new ArrayList<>(cast); //Defensive copy that maximizes encapsulation
	}

	public void setCast(List<Actor> cast) {
		this.cast = cast;
		// this.cast= new ArrayList<>(cast);
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
	
	public double getReplacementcost() {
		return replacementcost;
	}

	public void setReplacementcost(double replacementcost) {
		this.replacementcost = replacementcost;
	}

	// method to add Actor to the list
	public boolean addActor(Actor charlie) {
		if (cast == null) {
			cast = new ArrayList<>();
		}
		return cast.add(charlie);
	}

	public boolean removeActor(Actor charlie) {
		if (cast != null) {
			return cast.remove(charlie);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", description=" + description + ", releaseyear=" + releaseyear
				+ ", rentalduration=" + rentalduration + ", rentalrate=" + rentalrate + ", length=" + length
				+ ", replacementcost=" + replacementcost + ", rating=" + rating + ", specialfeatures=" + specialfeatures
				+ ", cast=" + cast + "]";
	}

}
