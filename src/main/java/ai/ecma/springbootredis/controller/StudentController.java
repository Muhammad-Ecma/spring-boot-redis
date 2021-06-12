package ai.ecma.springbootredis.controller;

import ai.ecma.springbootredis.entity.Student;
import ai.ecma.springbootredis.model.ApiResponse;
import ai.ecma.springbootredis.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Muhammad Mo'minov
 * 08.06.2021
 */
@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentController {
    final StudentService studentService;

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable int id){
        ApiResponse response = studentService.getOne(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public HttpEntity<?> edit(@RequestBody Student student){
        return ResponseEntity.ok(studentService.update(student));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOne(@PathVariable int id){
        return ResponseEntity.ok(studentService.deleteOne(id));
    }

    @DeleteMapping("/all")
    public HttpEntity<?> deleteAll(){
        return ResponseEntity.ok(studentService.deleteAll());
    }
}
