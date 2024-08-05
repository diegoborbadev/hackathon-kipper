package dev.diegoborba.hackathonkipper;

import dev.diegoborba.hackathonkipper.model.User;
import dev.diegoborba.hackathonkipper.service.UserService;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class UserServiceIntegrationTest extends AbstractCrudTest<UserService, User> {

    @Override
    protected User createTestElement() {
        User user = new User();
        user.setName("Name");
        return user;
    }

    @Override
    protected void updateTestElement(User user) {
        user.setName("Updated Name");
    }
}