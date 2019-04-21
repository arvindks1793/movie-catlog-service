package com.ks.moviecatlogservice.model;

public abstract class MovieCatlogModel {

	private int id;
	private String userId;
	private int movieRating;
	private int movieID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MovieCatlogModel() {

	}

	public MovieCatlogModel(int id, String userId, int movieRating, int movieID) {
		super();
		this.id = id;
		this.userId = userId;
		this.movieRating = movieRating;
		this.movieID = movieID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(int movieRating) {
		this.movieRating = movieRating;
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

}
