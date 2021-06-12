package ai.ecma.springbootredis.service;

import ai.ecma.springbootredis.entity.Student;
import ai.ecma.springbootredis.model.ApiResponse;
import ai.ecma.springbootredis.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Author: Muhammad Mo'minov
 * 08.06.2021
 */
@Service
@AllArgsConstructor
public class StudentService {
    final StudentRepository studentRepository;


    public ApiResponse getOne(int id) {
        sleep();
        return ApiResponse.success(studentRepository.findById(id).orElseGet(Student::new));
    }

    public ApiResponse update(Student student) {
        Student one = studentRepository.getOne(student.getId());
        one.setName(student.getName());
        one.setAge(student.getAge());
        return ApiResponse.success(studentRepository.save(one));
    }

    public ApiResponse save(Student student) {
        return ApiResponse.success(studentRepository.save(student));
    }

    public ApiResponse deleteOne(int id) {
        studentRepository.deleteById(id);
        return ApiResponse.success("Successfully deleted!");
    }

    public ApiResponse deleteAll() {
        studentRepository.deleteAll();
        return ApiResponse.success("Successfully deleted!");
    }

    public void sleep() {
        try {
            System.err.println("I am sleeping..........");
//            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}