package com.mwu.jediscrud.repository;

import com.mwu.jediscrud.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {}
