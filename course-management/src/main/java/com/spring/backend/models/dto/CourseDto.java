package com.spring.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

	private int id;
	
	@NonNull
	private String courseName;
	
	@NonNull
	private String description;
	
	private int duration;
	
	private double courseFees;
	
}
