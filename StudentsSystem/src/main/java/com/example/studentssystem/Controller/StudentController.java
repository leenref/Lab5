package com.example.studentssystem.Controller;

import com.example.studentssystem.ApiResponse.ApiResponse;
import com.example.studentssystem.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    ArrayList<Student> students = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Student> getStudents() {
        return students;

    }

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student) {
        students.add(student);
        return new ApiResponse("Student has been added");

    }

    @PutMapping("/update/{id}")
    public ApiResponse updateStudent(@PathVariable String id, @RequestBody Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.set(i, student);
                return new ApiResponse("Student has been updated");
            }
        }
        return new ApiResponse("Student not found");

    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteStudent(@PathVariable String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);
                return new ApiResponse("Student has been deleted");
            }
        }
        return new ApiResponse("Student not found");

    }

    //theres somthing wrong with the output
    @GetMapping("/honers")
    public ApiResponse honorsCategories() {
        ArrayList<Student> firstHonor = new ArrayList<>();
        ArrayList<Student> secondHonor = new ArrayList<>();

        for (Student s : students) {
            if (s.getGpa() >= 3.75) {
                firstHonor.add(s);
            } else if (s.getGpa() < 3.75 && s.getGpa() >= 3.25) {
                secondHonor.add(s);
            }
        }

        if (!firstHonor.isEmpty() || !secondHonor.isEmpty()) {
            return new ApiResponse("First honors: " + firstHonor + ", Second honors: " + secondHonor);
        } else {
            return new ApiResponse("No students have an honor degree.");
        }
    }


    @GetMapping("avg")
    public ApiResponse avg() {
        double sum = 0;
        for (Student s : students) {
            sum += s.getGpa();
        }

        double avg = sum / students.size();

        ArrayList<Student> aboveAverage = new ArrayList<>();
        for (Student s : students) {
            if (s.getGpa() > avg) {
                aboveAverage.add(s);
            }
        }

        if (aboveAverage.isEmpty()) {
            return new ApiResponse("No students have GPA above average.");
        }

        return new ApiResponse("Students with above average GPA: " + aboveAverage);
    }
}
