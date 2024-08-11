package org.vamshi.myjwt.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.vamshi.myjwt.Entity.Employee;
import org.vamshi.myjwt.Entity.Grantroles;

import java.util.Optional;

public interface Employeerepo extends JpaRepository<Employee,Long> {
    Employee save(Employee employee) ;


    Optional<Employee> findByEmail(String email);
Employee findByRole(Grantroles role);
}
