package com.example.Student_managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Student_managment.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}