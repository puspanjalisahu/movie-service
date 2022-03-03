package com.movieservice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieservice.exception.MovieException;
import com.movieservice.model.MovieEntity;
import com.movieservice.repository.MovieRepository;
import com.movieservice.service.MovieService;
import com.movieservice.util.MovieUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class MovieServiceImpl implements MovieService {
@Autowired
private MovieRepository movieRepo;
	
	public List<MovieEntity> getAllMovies() {
		List<MovieEntity> movies = new ArrayList<MovieEntity>();
		movieRepo.findAll().forEach(movies::add);
		
		return movies;
	}

	public MovieEntity addMovie(MovieEntity movie) throws MovieException {
		String id = MovieUtil.generateId(movie.getName(), movie.getReleaseDate());
		Optional<MovieEntity>  existingMovie = movieRepo.findById(id);
		if(existingMovie.isPresent()) {
			throw new MovieException("The Movie is already present !!!");
		}
		movie.setId(id);
		MovieEntity newMovie = movieRepo.save(movie);
		return newMovie;
	}

	@Override
	public MovieEntity updateMovie(String name, String releaseYear,MovieEntity inputMovie) throws MovieException {
		
		List<MovieEntity> existingMovies = null;
		if(MovieUtil.isEmpty(releaseYear)) {
			existingMovies = movieRepo.findByTitle(name);
			if(existingMovies ==null || existingMovies.isEmpty()) {
				throw new MovieException("The movie not found for the provided name, please enter correct name!!!");
			}else if(existingMovies.size() > 1) {
				throw new MovieException("Multiple movies found for same name, please enter release year to identify correct movie !!!");
			}
		}else {
			existingMovies = movieRepo.findByNameAndYear(name,MovieUtil.formSearchCriteria(releaseYear));
			if(existingMovies ==null || existingMovies.isEmpty()) {
				throw new MovieException("The movie not found for the provided name and year, please enter correct data !!!");
			}
		}
		
		MovieEntity extMovie = existingMovies.get(0);
		if(inputMovie.getRating() !=null) {
			extMovie.setRating(inputMovie.getRating());
		}
		if(inputMovie.getReleaseDate() != null) {
			extMovie.setReleaseDate(inputMovie.getReleaseDate());
		}
		if(inputMovie.getReleaseDate() != null && !inputMovie.getName().trim().isEmpty()) {
			extMovie.setTitle(inputMovie.getName());
		}
		MovieEntity newMovie = movieRepo.save(extMovie);
		return newMovie;
	}

	@Override
	public List<MovieEntity> findMoviesByRating(Short rating) {
		List<MovieEntity> movies = new ArrayList<MovieEntity>();
		movieRepo.findByRating(rating).forEach(movies::add);
		return movies;
	}

	@Override
	public List<MovieEntity> findMoviesByYear(String releaseYear) {
		List<MovieEntity> movies = new ArrayList<MovieEntity>();
		movieRepo.findByYear(MovieUtil.formSearchCriteria(releaseYear)).forEach(movies::add);
		return movies;
	}

}
