package com.studentapi.repository;

import com.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByCourseIgnoreCase(String course);

    Optional<Student> findTopByOrderByMarksDesc();

    @Query("select avg(s.marks) from Student s")
    Double findAverageMarks();
}
