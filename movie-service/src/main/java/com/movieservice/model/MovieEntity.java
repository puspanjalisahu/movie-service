package com.movieservice.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * Having issue in eclipse IDE so added getter and setter @Data automatically support getter and setter
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class MovieEntity {
	
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name = "name")
	private String name;
	// date format pattern="dd-MM-yyyy"
    @JsonFormat(shape=JsonFormat.Shape.STRING)
	@Column(name = "releaseDate")
	private Date releaseDate ;
	
	@Column(name = "rating")
	private Short rating;
	
	@Column(name = "creationTimestamp")
	@CreationTimestamp
	private Timestamp creationTime;
	
	@Column(name = "updateTimestamp")
	@UpdateTimestamp
	private Timestamp updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setTitle(String name) {
		this.name = name;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Short getRating() {
		return rating;
	}

	public void setRating(Short rating) {
		this.rating = rating;
	} 
	
}
