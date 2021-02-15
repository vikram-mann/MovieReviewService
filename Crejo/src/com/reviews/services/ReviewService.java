package com.reviews.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.reviews.components.*;
import com.reviews.exceptions.*;

public class ReviewService {
	
	static List<User> list_of_users = new ArrayList<>();
	static List<Movie> list_of_movies = new ArrayList<>();
	static List<Review> list_of_reviews = new ArrayList<Review>();

	public static void main(String[] args) 
	{
		/* Adding Users */
		add_user("SRK");
		add_user("Salman");
		add_user("Amir");
		
		/* Adding Movies */
		add_movie("Don", 2006, "Action, Crime");
		add_movie("Gangster", 2006, "Romance, Action");
		add_movie("Padmavat", 2008, "Mythological");
		add_movie("Infinity War", 2008, "Action, Sci-Fi");
		add_movie("Fast9", 2008, "Action");
		add_movie("Bahubali", 2010, "Action, War");
		add_movie("Inception", 2010, "Sci-Fi");
		add_movie("Interstellar", 2008, "Sci-Fi");
		add_movie("Avatar", 2010, "Sci-Fi");
		add_movie("XMen", 2006, "Action, Sci-Fi");
		
		/* Adding Reviews */
		try {
			add_review("SRK", "Done", 7);
		} 
		catch (UserNotFound | MovieNotFound | DuplicateReview e1) 
		{
			e1.printStackTrace();
		}
		
		try {
			add_review("Salman", "Gangster", 6);
		} 
		catch (UserNotFound | MovieNotFound | DuplicateReview e1) 
		{
			e1.printStackTrace();
		}
		
		try {
			add_review("Amir", "Don", 8);
		} 
		catch (UserNotFound | MovieNotFound | DuplicateReview e1) 
		{
			e1.printStackTrace();
		}
		
		try {
			add_review("SRK", "Fast9", 8);
		} 
		catch (UserNotFound | MovieNotFound | DuplicateReview e1) 
		{
			e1.printStackTrace();
		}
		
		try {
			add_review("SRK", "Infinity War", 7);
		} 
		catch (UserNotFound | MovieNotFound | DuplicateReview e1) 
		{
			e1.printStackTrace();
		}
		
		try {
			add_review("SRK", "Fast9", 7);
		}
		catch (UserNotFound | MovieNotFound | DuplicateReview e1) 
		{
			e1.printStackTrace();
		}
		
		/*
		System.out.println("List of Users:");
		
		for(User user : list_of_users)
		{
			System.out.println(user.toString());
		}
		
		System.out.println();
		System.out.println("List of Movies:");
		
		for(Movie mov : list_of_movies)
		{
			System.out.println(mov.toString());
		}
		
		System.out.println();
		System.out.println("List of Reviews:");
		
		
		for(Review rev : list_of_reviews)
		{
			System.out.println(rev.toString());
		}
		
		System.out.println();
		System.out.println("Average Rating for Movie Don is: ");
		try {
			avg_review_movie("Don");
		} catch (MovieNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("Average Rating for year 2006 is: ");
		avg_review_year(2006);
		
		System.out.println();
		System.out.println("Top Movies in 2006 with Action: ");
		top_movies(2006, "Action");
		*/
		
	}
	
	static void add_user(String name)
	{
		list_of_users.add(new User(name));
	}

	static void add_movie(String name, Integer year, String...genres)
	{
		list_of_movies.add(new Movie(name, year, genres));
	}
	
	static void add_review(String userName, String movieName, Integer rating) throws UserNotFound, MovieNotFound, DuplicateReview
	{		
		User user = list_of_users.stream()
					.filter(u -> userName.equals(u.getName()))
					.findAny()
					.orElseThrow(() -> new UserNotFound("User Not Found"));
		
		Movie movie = list_of_movies.stream()
						.filter(m -> movieName.equals(m.getName()))
						.findAny()
						.orElseThrow(()-> new MovieNotFound("Movie Not Released yet"));
		
		
		if(list_of_reviews.stream()
				.filter(r -> movieName.equals(r.getMovie().getName()))
				.filter(r -> userName.equals(r.getUser().getName()))
				.findFirst()
				.isPresent())
		{
			throw new DuplicateReview("Review Already Exists");
		}
		else
		{
			user.add_review(movie, rating);
			if(user.getMovies_reviewed().size()>=3) user.setUser_type("Critic");
			
			if(user.getUser_type().equals("Viewer"))
				movie.add_viewers_rating(user, rating);
			else
				movie.add_critics_rating(user, rating);
			
			list_of_reviews.add(new Review(user, movie, rating));
		}
		
	}
	
	static void avg_review_movie(String movieName) throws MovieNotFound
	{
		Movie movie = list_of_movies.stream()
				.filter(Movie -> movieName.equals(Movie.getName()))
				.findAny()
				.orElseThrow(()-> new MovieNotFound("Movie Not Released yet"));
	
		System.out.println(movie.avg_rating());
	}
	
	static void avg_review_year(Integer year)
	{
		Double sum = 0.0;
		Integer movie_count = 0;
	
		
		for(Movie movie : list_of_movies)
			if(year.equals(movie.getYear())) 
			{ 
				sum += movie.avg_rating();
				movie_count++;
			}
		Double avg = sum/movie_count;
		
		System.out.println(avg);
	}
	
	static void top_movies(Integer year, String genre)
	{
		Double sum = 0.0;
		Integer movie_count = 0;
		for(Movie movie : list_of_movies)
		{
			if(
				(Arrays.toString(movie.getGenre()).indexOf(genre) != -1)
				&& 
				(year.equals(movie.getYear()))
			)
			{
				/*
				 * System.out.println(movie.getName()); System.out.println(movie.critic_avg());
				 */
				sum += movie.critic_avg();
				movie_count++;
			}
		}
		
		System.out.println(sum/movie_count);
	}
}
