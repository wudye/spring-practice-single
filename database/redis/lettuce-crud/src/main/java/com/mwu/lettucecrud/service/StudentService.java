package com.mwu.lettucecrud.service;

import com.mwu.redis.model.Student;
import com.mwu.redis.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student save() {

        Student student = new Student(
                "Eng2015001", "John Doe", Student.Gender.MALE, 1);
        return studentRepository.save(student);
    }

    public Student findById() {
        Student retrievedStudent =
                studentRepository.findById("Eng2015001").get();
        return retrievedStudent;
    }
    public Student update() {
        Student retrievedStudent =
                studentRepository.findById("Eng2015001").get();
        retrievedStudent.setName("Richard Watson");
        return  studentRepository.save(retrievedStudent);
    }
    public void delete(String id) {

        studentRepository.deleteById(id);

    }
}
