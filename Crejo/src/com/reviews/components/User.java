package com.reviews.components;

import java.util.HashMap;
import java.util.Map;

public class User 
{
	private String name;
	private String user_type;
	private Map<Movie, Integer> movies_reviewed;
	
	public User(String name)
	{
		this.name = name;
		this.user_type = "Viewer";
		this.movies_reviewed = new HashMap<Movie, Integer>();
	}
	
	public void add_review(Movie movie, Integer rating)
	{
		movies_reviewed.put(movie, rating);
	}
	
	@Override
	public String toString() {
		
		return "["+this.name+", "+this.user_type+"]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public Map<Movie, Integer> getMovies_reviewed() {
		return movies_reviewed;
	}

	public void setMovies_reviewed(Map<Movie, Integer> movies_reviewed) {
		this.movies_reviewed = movies_reviewed;
	}
	
	
	
	
	

}
