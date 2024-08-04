package dev.diegoborba.hackathonkipper.repository;

import dev.diegoborba.hackathonkipper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}