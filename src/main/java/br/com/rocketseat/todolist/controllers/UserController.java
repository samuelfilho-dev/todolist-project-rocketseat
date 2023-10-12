package br.com.rocketseat.todolist.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.rocketseat.todolist.domain.models.UserModel;
import br.com.rocketseat.todolist.domain.repositories.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserModelRepository repository;

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody UserModel user) {
        var requestUser = this.repository.findByUsername(user.getUsername());

        if (requestUser != null)
            return new ResponseEntity<>("User Alerdy Exists", HttpStatus.BAD_REQUEST);

        var passwordHashed = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

        user.setPassword(passwordHashed);
        var dbUser = this.repository.save(user);

        return new ResponseEntity<>(dbUser, HttpStatus.CREATED);
    }
}
