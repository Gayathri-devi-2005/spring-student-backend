package com.example.Student_managment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.Student_managment.dto.StudentDTO;
import com.example.Student_managment.exception.ResourceNotFoundException;
import com.example.Student_managment.model.Student;
import com.example.Student_managment.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public StudentDTO createStudent(StudentDTO dto) {

        Student student = new Student(
                null,
                dto.getName(),
                dto.getEmail(),
                dto.getCourse(),
                dto.getAge()
        );

        Student saved = repository.save(student);
        return convertToDTO(saved);
    }

    // READ ALL
    public List<StudentDTO> getAllStudents() {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // READ BY ID
    public StudentDTO getStudentById(Long id) {

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id " + id));

        return convertToDTO(student);
    }

    // UPDATE
    public StudentDTO updateStudent(Long id, StudentDTO dto) {

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id " + id));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());
        student.setAge(dto.getAge());

        Student updated = repository.save(student);
        return convertToDTO(updated);
    }

    // DELETE
    public void deleteStudent(Long id) {

        Student student = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id " + id));

        repository.delete(student);
    }

    // ENTITY → DTO
    private StudentDTO convertToDTO(Student student) {

        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setCourse(student.getCourse());
        dto.setAge(student.getAge());

        return dto;
    }
}