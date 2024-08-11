package org.vamshi.myjwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.vamshi.myjwt.Entity.Employee;
import org.vamshi.myjwt.Entity.Grantroles;
import org.vamshi.myjwt.Repo.Employeerepo;

@SpringBootApplication
public class MyJwtApplication implements CommandLineRunner {
@Autowired
    private Employeerepo employeerepo;
    public static void main(String[] args) {
        SpringApplication.run(MyJwtApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Employee myemployee=employeerepo.findByRole(Grantroles.Admin);
        Employee employee=new Employee();
        employee.setEmail("admin@gmail.com");
        employee.setFirstname("admin");
        employee.setSecondname("admin");
        employee.setPassword(new BCryptPasswordEncoder().encode("admin"));
        employeerepo.save(employee);
    }
}
