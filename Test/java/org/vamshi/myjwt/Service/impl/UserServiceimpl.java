package org.vamshi.myjwt.Service.impl;

import org.vamshi.myjwt.Repo.Employeerepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vamshi.myjwt.Service.Userservice;

@Service
public class UserServiceimpl implements Userservice {
    private Employeerepo employeerepo;

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return employeerepo.findByEmail(username).
                        orElseThrow(() -> new UsernameNotFoundException("username not found"));
            }
        };
    }
}
