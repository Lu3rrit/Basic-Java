package org.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.example.demo.domain.Attendance;
import org.example.demo.dto.request.AttendanceRequest;
import org.example.demo.dto.request.AttendanceSearchRequest;
import org.example.demo.service.AttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/checkin")
    public ResponseEntity<Attendance> checkIn(@RequestBody AttendanceRequest request) {
        return ResponseEntity.ok(attendanceService.checkIn(request));
    }
    @GetMapping
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        return ResponseEntity.ok(attendanceService.getAllAttendance());
    }
    @GetMapping("/by-date")
    public ResponseEntity<List<Attendance>> getByDate(@RequestParam LocalDate date) {
        return ResponseEntity.ok(attendanceService.getAttendanceByDate(date));
    }
    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<List<Attendance>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByStudent(studentId));
    }
    @GetMapping("/search")
    public ResponseEntity<List<Attendance>> search(@RequestBody AttendanceSearchRequest searchRequest) {
        return ResponseEntity.ok(attendanceService.searchAttendance(searchRequest));
    }
}
