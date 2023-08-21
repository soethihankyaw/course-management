package com.spring.backend.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

	private int id;
	private String username;
	private String password;
	private String phone;
}
