package org.vamshi.myjwt.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vamshi.myjwt.Entity.Employee;
import org.vamshi.myjwt.Service.AuthenticationService;
import org.vamshi.myjwt.dto.JwtAuthenticationResponse;
import org.vamshi.myjwt.dto.Sigininrequest;
import org.vamshi.myjwt.dto.Signuprequest;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<Employee> signup(@RequestBody Signuprequest signuprequest){
        return ResponseEntity.ok(authenticationService.signup(signuprequest));
    }
    @PostMapping("signin")
    public ResponseEntity<JwtAuthenticationResponse> sigin(@RequestBody Sigininrequest sigininrequest){
        return  ResponseEntity.ok(authenticationService.siginin(sigininrequest));
    }

}
