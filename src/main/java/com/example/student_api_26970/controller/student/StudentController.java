package com.example.student_api_26970.controller.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.student_api_26970.model.student.Student;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> studentList = new ArrayList<>();

    public StudentController() {

        studentList.add(new Student(1L, "Aline", "Munyaneza", "aline@gmail.com", "Computer Science", 3.8));
        studentList.add(new Student(2L, "Eric", "Habimana", "eric@gmail.com", "Business", 3.0));
        studentList.add(new Student(3L, "Joy", "Uwase", "joy@gmail.com", "Software Engineering", 3.6));
        studentList.add(new Student(4L, "Patrick", "Ndayisaba", "patrick@gmail.com", "Computer Science", 2.9));
        studentList.add(new Student(5L, "Linda", "Mukamana", "linda@gmail.com", "Information Systems", 3.9));
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentList;
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Long studentId) {

        for (Student s : studentList) {
            if (s.getStudentId().equals(studentId)) {
                return s;
            }
        }

        throw new RuntimeException("Student not found");
    }

    @GetMapping("/major/{major}")
    public List<Student> getStudentsByMajor(@PathVariable String major) {

        List<Student> result = new ArrayList<>();

        for (Student s : studentList) {
            if (s.getMajor().equalsIgnoreCase(major)) {
                result.add(s);
            }
        }

        return result;
    }

    @GetMapping("/filter")
    public List<Student> filterByGpa(@RequestParam Double gpa) {

        List<Student> result = new ArrayList<>();

        for (Student s : studentList) {
            if (s.getGpa() >= gpa) {
                result.add(s);
            }
        }

        return result;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestBody Student student) {
        studentList.add(student);
        return student;
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable Long studentId,
                                 @RequestBody Student updatedStudent) {

        for (Student s : studentList) {
            if (s.getStudentId().equals(studentId)) {

                s.setFirstName(updatedStudent.getFirstName());
                s.setLastName(updatedStudent.getLastName());
                s.setEmail(updatedStudent.getEmail());
                s.setMajor(updatedStudent.getMajor());
                s.setGpa(updatedStudent.getGpa());

                return s;
            }
        }

        throw new RuntimeException("Student not found");
    }
}
