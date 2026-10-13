package com.studentapi.service;

import com.studentapi.exception.StudentNotFoundException;
import com.studentapi.model.Student;
import com.studentapi.model.StudentStats;
import com.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student create(Student student) {
        student.setId(null); // always let the database generate the id
        return repository.saveAndFlush(student);
    }

    public List<Student> findAll(String name, String course) {
        if (name != null && !name.isBlank()) {
            return repository.findByNameContainingIgnoreCase(name);
        }
        if (course != null && !course.isBlank()) {
            return repository.findByCourseIgnoreCase(course);
        }
        return repository.findAll();
    }

    public Student findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student update(Long id, Student updated) {
        Student existing = findById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setAge(updated.getAge());
        existing.setCourse(updated.getCourse());
        existing.setMarks(updated.getMarks());
        return repository.saveAndFlush(existing);
    }

    public void delete(Long id) {
        Student existing = findById(id);
        repository.delete(existing);
    }

    public StudentStats stats() {
        long total = repository.count();
        Double avg = repository.findAverageMarks();
        Student topper = repository.findTopByOrderByMarksDesc().orElse(null);
        return new StudentStats(
                total,
                avg == null ? 0.0 : Math.round(avg * 100.0) / 100.0,
                topper == null ? null : topper.getName(),
                topper == null ? null : topper.getMarks());
    }
}
