package com.example.studenttracker.controllers;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.studenttracker.models.Student;
import com.example.studenttracker.models.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;





@Controller
public class StudentController
{
    @Autowired
    private StudentRepository studentRepo;
    @GetMapping("/student/view")
    public String getAllStudent(Model model)
    {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("stu", students);
        return "student/showAll";
    }

    @GetMapping("/student/viewtable")
    
    public String getAllStudentsTable(Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("stu", students);
        return "student/tableAll";
    }

    // Post mapping to add new student
    @PostMapping("/student/add")
    public String addStudent(@RequestParam Map<String, String> newstudent, HttpServletResponse response) {
        System.out.println("Add Student");
        String newName = newstudent.get("name");
        int newWeight = Integer.parseInt(newstudent.get("weight"));
        int newHeight = Integer.parseInt(newstudent.get("height"));
        String newHairColor = newstudent.get("haircolor");
        double newGpa = Double.parseDouble(newstudent.get("gpa"));
        studentRepo.save(new Student(newName, newWeight, newHeight, newHairColor, newGpa));
        response.setStatus(201);
        return "student/finish";
    }

    // Get mapping to show all students data in the table
    @GetMapping("/student/deltable")
    public String delStudentTable(Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("stu", students);
        return "student/delete";
    }
    
    // Post mapping to delete selected students
    @SuppressWarnings("null")
    @PostMapping("/student/delete")
    public String delStudents(@RequestParam("studentIds") List<Integer> studentIds, HttpServletResponse response) {
        studentRepo.deleteAllById(studentIds);
        response.setStatus(201);
        return "student/finish";
    }

    @GetMapping("/student/edi")
    public String ediStudentTable(Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("stu", students);
        return "student/editing";
    }

    //@SuppressWarnings("null")
    @PostMapping("/student/editing")
    public String editStudent(@RequestParam("name") List<String> names,
                          @RequestParam("weight") List<Integer> weights,
                          @RequestParam("height") List<Integer> heights,
                          @RequestParam("hairColor") List<String> hairColors,
                          @RequestParam("gpa") List<Double> gpas,
                        HttpServletResponse response) {

    
        List<Student> students = studentRepo.findAll();

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            String name = names.get(i);
            Integer weight = weights.get(i);
            Integer height = heights.get(i);
            String haircolor = hairColors.get(i);
            Double gpa = gpas.get(i);

            student.setName(name);
            student.setWeight(weight);
            student.setHeight(height);
            student.setHairColor(haircolor);
            student.setGpa(gpa);

            studentRepo.save(student);
        }

        response.setStatus(201);
        return "student/finish";
    }
    

    
}
