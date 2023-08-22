package com.spring.backend.models.dto;

import com.spring.backend.models.Teacher;

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
public class TeacherDto {

	private int id;
	private String username;
	private String password;
	private String phone;
}
