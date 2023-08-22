package com.spring.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

	private int id;
	private String courseName;
	private String description;
	private int duration;
	private double courseFees;
	
}
