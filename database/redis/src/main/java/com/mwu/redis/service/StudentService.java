package com.mwu.redis.service;

import com.mwu.redis.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private RedisTemplate studentRepository;

    public Student save() {

        Student student = new Student(
                "Eng2015001", "John Doe", Student.Gender.MALE, 1);
         studentRepository.opsForValue().set(student.getId(), student);
        return student;
    }

    public Student findById() {
        Student retrievedStudent =
                (Student) studentRepository.opsForValue().get("Eng2015001");
        return retrievedStudent;
    }
    public Student update() {
        Student retrievedStudent =
                findById();
        retrievedStudent.setName("Richard Watson");
        studentRepository.opsForValue().set(retrievedStudent.getId(), retrievedStudent);
        return retrievedStudent;
    }
    public void delete(String id) {

        studentRepository.delete(id);

    }
}
