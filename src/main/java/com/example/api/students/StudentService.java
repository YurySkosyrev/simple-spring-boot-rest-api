package com.example.api.students;

import com.example.api.response.RestApiBodException;
import com.example.api.response.RestApiException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> list() {
        return studentRepository.findAll();
    }

    public List<Student> add(Student student) {
        if(studentRepository.findStudentByEmail(student.getEmail()).isPresent()) {
            throw new RestApiException("Email is busy");
        }

        if(student.getDob().isAfter(LocalDate.of(2005, Month.JANUARY, 1))) {
            throw new RestApiBodException("Student is too young");
        }

        studentRepository.save(student);
        return studentRepository.findAll();
    }

    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void update(Student student) {
        Optional<Student> raw = studentRepository.findById(student.getId());
        if(raw.isPresent()){
            Student item = raw.get();
            if (student.getName() != null){
                item.setName(student.getName());
            }
            if (student.getDob() != null){
                item.setDob(student.getDob());
            }
            studentRepository.save(item);
        }
    }
}
