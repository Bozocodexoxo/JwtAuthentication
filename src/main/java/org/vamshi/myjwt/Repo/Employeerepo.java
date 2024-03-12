package org.vamshi.myjwt.Repo;

import com.vamshi.springbootjwt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface Employeerepo extends JpaRepository<Employee,Long> {
   Optional<Employee> findByEmail(String email);
}
