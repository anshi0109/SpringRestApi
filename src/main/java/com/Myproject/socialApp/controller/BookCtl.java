package com.Myproject.socialApp.controller;

import com.Myproject.socialApp.entity.Student;
import com.Myproject.socialApp.repository.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class BookCtl {

    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/")
    public List<Student> getAllStudents(){
    return studentRepo.findAll();
    }

    @PostMapping("/post")
    public Student create(@RequestBody Student student){
        return studentRepo.save(student);
    }
    //delete the student by using id
    @DeleteMapping("/{id}")
    public boolean deleteByID(@PathVariable Long id){
        Optional<Student> s=studentRepo.findById(id);
        if(s.isPresent()){
            studentRepo.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
    //updating student by id

    @PutMapping("/{id}")
    public Student updateById(@PathVariable Long id,@RequestBody Student student){
        Student update=studentRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Student not found"));
        update.setName(student.getName());
        update.setEmail(student.getEmail());
        return studentRepo.save(update);
    }
}