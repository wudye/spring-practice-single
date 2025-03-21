package com.mwu.lettucecrud.repository;

import com.mwu.redis.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {}
