package org.vamshi.myjwt.Service;

import org.vamshi.myjwt.Entity.Employee;
import org.vamshi.myjwt.dto.JwtAuthenticationResponse;
import org.vamshi.myjwt.dto.Sigininrequest;
import org.vamshi.myjwt.dto.Signuprequest;

public interface AuthenticationService {
    Employee signup(Signuprequest signuprequest);
    JwtAuthenticationResponse siginin(Sigininrequest sigininrequest);
}
