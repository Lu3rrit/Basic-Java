package org.example.demo.service;

import lombok.AllArgsConstructor;
import org.example.demo.domain.Attendance;
import org.example.demo.domain.Student;
import org.example.demo.dto.request.AttendanceRequest;
import org.example.demo.dto.request.AttendanceSearchRequest;
import org.example.demo.repository.AttendanceRepository;
import org.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    public Attendance checkIn(AttendanceRequest request) {
        Long studentId = request.getStudentId();
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Sinh viên không tồn tại với ID: " + studentId));

        LocalDate date = request.getAttendanceDate() != null ? request.getAttendanceDate() : LocalDate.now();
        String classCode = request.getClassCode();

        Attendance existing = attendanceRepository.findByStudentIdAndAttendanceDateAndClassCode(studentId, date, classCode);
        if (existing != null) {
            throw new RuntimeException("Sinh viên đã điểm danh hôm nay cho lớp: " + classCode);
        }

        Attendance attendance = new Attendance();
        attendance.setStudentId(studentId);
        attendance.setClassCode(classCode);
        attendance.setAttendanceDate(date);
        attendance.setStatus("PRESENT");
        return attendanceRepository.save(attendance);
    }
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
    public List<Attendance> getAttendanceByDate(LocalDate date) {
        return attendanceRepository.findByAttendanceDate(date);
    }
    public List<Attendance> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }
    public List<Attendance> searchAttendance(AttendanceSearchRequest searchRequest) {
        return attendanceRepository.searchAttendance(
                searchRequest.getStudentId(),
                searchRequest.getDate(),
                searchRequest.getStatus()
        );
    }
}
