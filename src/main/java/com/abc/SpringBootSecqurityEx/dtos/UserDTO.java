package com.abc.SpringBootSecqurityEx.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserDTO {

    @Size(max = 255)
    private String userName;

    @Size(max = 255)
    private String userFirstName;

    @Size(max = 255)
    private String userLastName;

    @Size(max = 255)
    private String password;

    @NotNull
    @Size(max = 255)
    private String email;

    private Boolean enabled;

    private Boolean credentialsNonExpired;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private List<String> roles;

}
