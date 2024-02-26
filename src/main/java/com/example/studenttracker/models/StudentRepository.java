package com.example.studenttracker.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>
{
    List<Student> findByNameAndWeightAndHeight(String name, int weight, int height);
    List<Student> findByUid(int uid);
    
}
