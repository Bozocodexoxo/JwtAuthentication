package org.vamshi.myjwt.dto;

import lombok.Data;

@Data
public class Signuprequest {
    private  String firstname;
    private String lastname;
    private String email;
    private String password;

}
