package com.movieservice.cotroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movieservice.exception.MovieException;
import com.movieservice.model.MovieEntity;
import com.movieservice.service.MovieService;
import com.movieservice.util.MovieUtil;

@RestController
@RequestMapping("/api/movies")
public class MovieServiceController {
	@Autowired
	private MovieService service;

	@GetMapping("/getAllMovies")
	public ResponseEntity<List<MovieEntity>> getAllMovies() {
		try {
			
			List<MovieEntity> movies = service.getAllMovies();
				if (movies.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(movies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getMoviesByRating/{rating}")
	public ResponseEntity<List<MovieEntity>> getMoviesByRating(@PathVariable(name="rating",required=true) Short rating) {
		try {
			
			List<MovieEntity> movies = service.findMoviesByRating(rating);
			
			 if (movies.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(movies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getMoviesByYear")
	public ResponseEntity<List<MovieEntity>> getMoviesByYear(@RequestParam(name="releaseYear",required=true) String releaseYear) {
		try {
			List<MovieEntity> movies = service.findMoviesByYear(releaseYear);
				if (movies.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(movies, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/createMovie")
	public ResponseEntity<String> addMovie(@RequestBody(required=true) MovieEntity movie) {
		try {
			if(movie == null) {
				return new ResponseEntity<>("The input cann't be empty !!!", HttpStatus.BAD_REQUEST);
			}
			if(MovieUtil.isEmpty(movie.getName())) {
				return new ResponseEntity<>("The name input parameter cann't be empty !!!", HttpStatus.BAD_REQUEST);
			}else if(MovieUtil.validateDate(movie.getReleaseDate())) {
				return new ResponseEntity<>("Enter date 'dd-mm-yyyy' format !!!", HttpStatus.BAD_REQUEST);
			}
			service.addMovie(movie);
			return new ResponseEntity<>("Movie added successfully !!!", HttpStatus.CREATED);
		} catch (MovieException m) {
			return new ResponseEntity<>(m.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updateMovie")
	public ResponseEntity<String> updateMovie(@RequestParam(name="name",required=true) String name, @RequestParam(name="year", required=false) String  releaseYear,@RequestBody MovieEntity movie) {
		try {
			
			if(movie == null) {
				return new ResponseEntity<>("The input cann't be empty !!!", HttpStatus.BAD_REQUEST);
			}
			if(MovieUtil.isEmpty(name)) {
				return new ResponseEntity<>("The name parameter cann't be empty !!!", HttpStatus.BAD_REQUEST);
			}
			if(!MovieUtil.isEmpty(releaseYear) && !MovieUtil.validYear(releaseYear)) {
				return new ResponseEntity<>("Enter valid year !!!", HttpStatus.BAD_REQUEST);
			}
			service.updateMovie(name, releaseYear,movie);
			return new ResponseEntity<>("The movie updated Successfull !!!", HttpStatus.CREATED);
		} catch (MovieException m) {
			return new ResponseEntity<>(m.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
