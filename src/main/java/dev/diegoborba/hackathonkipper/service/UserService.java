package dev.diegoborba.hackathonkipper.service;

import dev.diegoborba.hackathonkipper.model.User;
import dev.diegoborba.hackathonkipper.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;

@Service
public class UserService extends CrudServiceJpaImpl<UserRepository, User, Long> {

    public Optional<User> updateName(Long id, String name) {
        return patchUser(id, user -> user.setName(name));
    }

    public Optional<User> addScore(Long id, Long value) {
        return patchUser(id, user -> user.setScore(user.getScore() + value));
    }

    private Optional<User> patchUser(Long id, Consumer<User> userUpdater) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isEmpty()) return Optional.empty();

        User user = userOptional.get();
        userUpdater.accept(user);
        return updateElement(user);
    }
}