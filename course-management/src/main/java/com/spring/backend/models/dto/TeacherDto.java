package com.spring.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

	private int id;
	private String courseName;
	private String description;
	private int duration;
	private double courseFees;
}
