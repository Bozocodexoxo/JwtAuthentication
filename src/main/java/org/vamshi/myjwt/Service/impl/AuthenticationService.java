package org.vamshi.myjwt.Service.impl;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vamshi.myjwt.Entity.Employee;
import org.vamshi.myjwt.Entity.Grantroles;
import org.vamshi.myjwt.Repo.Employeerepo;
import org.vamshi.myjwt.Service.jwtservice;
import org.vamshi.myjwt.dto.JwtAuthenticationResponse;
import org.vamshi.myjwt.dto.Sigininrequest;
import org.vamshi.myjwt.dto.Signuprequest;

import javax.management.relation.Role;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements org.vamshi.myjwt.Service.AuthenticationService {
private final Employeerepo employeerepo;
private final PasswordEncoder passwordencoder;
private final AuthenticationManager authenticationManager;
private final jwtservice jwtservice;
public Employee signup(Signuprequest signuprequest){
    Employee employee=new Employee();
    employee.setEmail(signuprequest.getEmail());
    employee.setFirstname(signuprequest.getFirstname());
    employee.setSecondname(signuprequest.getLastname());
    employee.setRoles(Grantroles.User);
    employee.setPassword(passwordencoder.encode(signuprequest.getPassword()));
    return employeerepo.save(employee);

}
public JwtAuthenticationResponse siginin(Sigininrequest sigininrequest){
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sigininrequest.getEmail(),sigininrequest.getPassword()));
    var employee=employeerepo.findByEmail(sigininrequest.getEmail()).orElseThrow(()->new IllegalArgumentException("enter properargs"));
    var jwt=jwtservice.GenerateToken(employee);
    var refreshyoken=jwtservice.generateRefreshtok(new HashMap<>(),employee);
    JwtAuthenticationResponse jwtAuthenticationResponse=new JwtAuthenticationResponse();
    jwtAuthenticationResponse.setToken(jwt);
    jwtAuthenticationResponse.setRefreshtoken(refreshyoken);
    return jwtAuthenticationResponse;
}
}