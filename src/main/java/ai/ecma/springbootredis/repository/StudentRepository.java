package ai.ecma.springbootredis.repository;

import ai.ecma.springbootredis.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Muhammad Mo'minov
 * 08.06.2021
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
