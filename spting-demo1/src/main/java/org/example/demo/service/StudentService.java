package org.example.demo.service;

import lombok.AllArgsConstructor;
import org.example.demo.domain.Student;
import org.example.demo.dto.request.StudentRequest;
import org.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public Student createStudent(StudentRequest student) {
        Student oldStudent = studentRepository.findByCode(student.getStudentCode());
        if (oldStudent != null) {
            throw new RuntimeException("Code đã tồn tại");
        }
        long count = studentRepository.count();
        if (count >= 100) {
            throw new RuntimeException("Cannot create more than 100 students");
        }

        Student newStudent = new Student();
        newStudent.setCode(student.getStudentCode());
        newStudent.setStudentName(student.getStudentName());
        newStudent.setAge(student.getStudentAge());
        return studentRepository.save(newStudent);
    }

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsByClassCode(String classCode) {
        return studentRepository.findByClassCode(classCode);
    }

    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found ID: " + id));
    }

    @Transactional
    public Student updateStudent(Long id, StudentRequest studentRequest) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found student with ID: " + id));
        Student studentWithCode = studentRepository.findByCode(studentRequest.getStudentCode());
        if (studentWithCode != null && !studentWithCode.getStudentId().equals(id)) {
            throw new RuntimeException("ID has existed!");
        }
        existingStudent.setCode(studentRequest.getStudentCode());
        existingStudent.setStudentName(studentRequest.getStudentName());
        existingStudent.setAge(studentRequest.getStudentAge());
        return studentRepository.save(existingStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Not found student with ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}
