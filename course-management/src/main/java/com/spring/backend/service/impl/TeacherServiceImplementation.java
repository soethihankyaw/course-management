package com.spring.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.backend.models.Teacher;
import com.spring.backend.models.dto.TeacherDto;
import com.spring.backend.repository.TeacherRepository;
import com.spring.backend.service.TeacherService;

@Service
public class TeacherServiceImplementation implements TeacherService{

	@Autowired
	private TeacherRepository teacherRepository;
	
	//create teacher
	@Override
	public TeacherDto createTeacher(TeacherDto teacherDto) {
		Teacher teacher = new Teacher();
		teacher.setUsername(teacherDto.getUsername());
		teacher.setPassword(teacherDto.getPassword());
		teacher.setPhone(teacherDto.getPhone());
		
		Teacher newTeacher = teacherRepository.save(teacher);
		
		TeacherDto teacherResponse = new TeacherDto();
		teacherResponse.setId(newTeacher.getId());
		teacherResponse.setUsername(newTeacher.getUsername());
		teacherResponse.setPassword(newTeacher.getPassword());
		teacherResponse.setPhone(newTeacher.getPhone());
		
		return teacherResponse;
	}
	
	//entity to dto
	private TeacherDto mapToDto(Teacher teacher) {
		
		TeacherDto teacherDto = new TeacherDto();
		teacherDto.setId(teacher.getId());
		teacherDto.setUsername(teacher.getUsername());
		teacherDto.setPassword(teacher.getPassword());
		teacherDto.setPhone(teacher.getPhone());
		return teacherDto;
	}
	
	//dto to entity
	private Teacher mapToEntity(TeacherDto teacherDto) {
		
		Teacher teacher = new Teacher();
		teacher.setId(teacherDto.getId());
		teacher.setUsername(teacherDto.getUsername());
		teacher.setPassword(teacherDto.getPassword());
		teacher.setPhone(teacherDto.getPhone());
		
		return teacher;
	}
	
}
