package org.vamshi.myjwt.Service.impl;

import com.vamshi.springbootjwt.Repository.Employeerepo;
import com.vamshi.springbootjwt.Service.Userservice;
import org.springframework.stereotype.Service;

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
