package com.movieservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.movieservice.exception.MovieException;
import com.movieservice.model.MovieEntity;

@Service
public interface MovieService {

	public List<MovieEntity> getAllMovies();
	
	public MovieEntity addMovie(MovieEntity movie) throws MovieException;
	
	public MovieEntity updateMovie(String name, String releaseYear,MovieEntity movie) throws MovieException;

	public List<MovieEntity> findMoviesByRating(Short rating);

	public List<MovieEntity> findMoviesByYear(String releaseYear);

}
