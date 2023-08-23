package com.spring.backend.models.dto;

import com.spring.backend.models.entity.Teacher;

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
public class TeacherDto {
	
	
	private int id;
	
	@NonNull
	private String name;
	
	@NonNull
	private String email;
	
	@NonNull
	private String phone;
}
