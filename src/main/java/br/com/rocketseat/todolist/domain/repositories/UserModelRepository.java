package br.com.rocketseat.todolist.domain.repositories;

import br.com.rocketseat.todolist.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserModelRepository extends JpaRepository<UserModel, UUID> {

    UserModel findByUsername(String username);
}
