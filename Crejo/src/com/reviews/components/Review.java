package com.reviews.components;

public class Review 
{
	private User user;
	private Movie movie;
	private Integer rating = 0;
	
	
	public Review(User user, Movie movie, Integer rating)
	{
		this.user = user;
		this.movie = movie;
		this.rating = rating;
	}
	
	@Override
	public String toString() {
		
		return "["+user.getName()+", "+movie.getName()+", "+rating+"]";
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	public Integer getRating() {
		return rating;
	}


	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	

}
