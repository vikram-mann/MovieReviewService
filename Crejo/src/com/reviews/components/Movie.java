package com.reviews.components;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Movie 
{
	private String name;
	private String[] genre;
	private Integer year;
	private Map<User, Integer> viewers_reviewed;
	private Map<User, Integer> critics_reviewed;
	
	
	
	public Movie(String name, Integer year, String[] genre) {
		super();
		this.name = name;
		this.year = year;
		this.genre = genre;
		this.viewers_reviewed = new HashMap<>();
		this.critics_reviewed = new HashMap<>();
	}
	
	public void add_viewers_rating(User user, Integer rating)
	{
		viewers_reviewed.put(user, rating);
	}
	
	public void add_critics_rating(User user, Integer rating)
	{
		critics_reviewed.put(user, rating);
	}
	
	public Double avg_rating()
	{
		Double sum = 0.0;
		
		if(viewers_reviewed.isEmpty())
			sum+= 0.0;
		else
			sum += viewers_reviewed.values().stream().reduce(0, Integer::sum);
		
		if(critics_reviewed.isEmpty())
			sum += 0.0;
		else
			sum += critics_reviewed.values().stream().reduce(0, Integer::sum);
		
		if(sum.equals(0.0))
			return 0.0;
		else
		{
			Double avg = sum/(viewers_reviewed.size() + critics_reviewed.size());
			
			return avg;
		}
	}
	
	public Double critic_avg()
	{
		if(critics_reviewed.isEmpty())
			return 0.0;
		else
		{
			Double sum = 0.0;
			
			sum += critics_reviewed.values().stream().reduce(0, Integer::sum);
			
			Double avg = sum/critics_reviewed.size();
			
			return avg;
		}
	}
	
	@Override
	public String toString() {
		
		return "["+this.name+", "+this.year+", "+Arrays.toString(this.genre)+"]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String[] getGenre() {
		return genre;
	}

	public void setGenre(String[] genre) {
		this.genre = genre;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Map<User, Integer> getUsers_reviewed() {
		return viewers_reviewed;
	}

	public void setUsers_reviewed(Map<User, Integer> users_reviewed) {
		this.viewers_reviewed = users_reviewed;
	}
	
	public Map<User, Integer> getViewers_reviewed() {
		return viewers_reviewed;
	}

	public void setViewers_reviewed(Map<User, Integer> viewers_reviewed) {
		this.viewers_reviewed = viewers_reviewed;
	}

	public Map<User, Integer> getCritics_reviewed() {
		return critics_reviewed;
	}

	public void setCritics_reviewed(Map<User, Integer> critics_reviewed) {
		this.critics_reviewed = critics_reviewed;
	}

	
	
	
	

}
