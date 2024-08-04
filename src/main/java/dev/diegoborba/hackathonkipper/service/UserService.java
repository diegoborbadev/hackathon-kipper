package dev.diegoborba.hackathonkipper.service;

import dev.diegoborba.hackathonkipper.model.User;
import dev.diegoborba.hackathonkipper.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends CrudServiceJpaImpl<UserRepository, User, Long> {

    public Optional<User> updateName(Long id, String name) {
        Optional<User> userOptional = repository.findById(id);
        userOptional.ifPresent(user -> {
            user.setName(name);
            this.updateElement(user);
        });
        return userOptional;
    }
}