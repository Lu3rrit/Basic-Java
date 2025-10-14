package org.example.demo.repository;

import org.example.demo.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByCode(String studentCode);
    List<Student> findByClassCode(String classCode);

}
