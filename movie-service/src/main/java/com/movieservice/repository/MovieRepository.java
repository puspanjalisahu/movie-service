package com.movieservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movieservice.model.MovieEntity;
@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, String>{

	public Optional<MovieEntity> findById(String id);
	public List<MovieEntity> findByTitle(String title);
	public List<MovieEntity> findByRating(Short rating);
	
	@Query(value = "select  * from MOVIE  where TO_CHAR(release_date,'YYYY/MM/DD')  like :releaseYear ", nativeQuery = true)
	public List<MovieEntity> findByYear(String releaseYear);
	
	@Query(value = "select  * from MOVIE  where TO_CHAR(release_date,'YYYY/MM/DD')  like :releaseYear and name=:name", nativeQuery = true)
	public List<MovieEntity> findByNameAndYear(String releaseYear,String name);
}
