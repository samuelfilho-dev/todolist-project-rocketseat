package br.com.rocketseat.todolist.domain.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserModel {

    private String username;
    private String name;
    private String password;

}
