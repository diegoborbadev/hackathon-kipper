package dev.diegoborba.hackathonkipper;

import dev.diegoborba.hackathonkipper.controller.UserController;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class HackathonKipperApplicationTests {

    @Test
    void contextLoads() {
        
    }
}