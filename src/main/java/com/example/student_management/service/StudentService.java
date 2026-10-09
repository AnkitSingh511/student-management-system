package com.example.student_management.service;
import com.example.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;
import com.example.student_management.entity.Student;
import com.example.student_management.exception.StudentNotFoundException;
import java.util.List;
@Service
public class StudentService{
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    public Student getStudentById(Long id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }
    public Student updatedStudent(Long id,Student updatedStudent){
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException("Student not found with id: " + id));
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setCourse(updatedStudent.getCourse());
       return studentRepository.save(existingStudent);
    }
    public void deleteStudent(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new StudentNotFoundException("Student not Found with id: " + id));
        studentRepository.delete(student);
    }
}
