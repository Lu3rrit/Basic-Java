package org.example.demo.repository;

import org.example.demo.domain.Attendance;
import org.example.demo.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Attendance findByStudentIdAndAttendanceDateAndClassCode(Long studentId, LocalDate date, String classCode);

    List<Attendance> findByAttendanceDate(LocalDate date);

    List<Attendance> findByStudentId(Long studentId);

    @Query("SELECT a FROM Attendance a WHERE (:studentId IS NULL OR a.studentId = :studentId) " +
            "AND (:date IS NULL OR a.attendanceDate = :date) " +
            "AND (:status IS NULL OR a.status = :status)")
    List<Attendance> searchAttendance(@Param("studentId") Long studentId,
                                      @Param("date") LocalDate date,
                                      @Param("status") String status);
}
