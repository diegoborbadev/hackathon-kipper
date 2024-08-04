package dev.diegoborba.hackathonkipper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User extends IdentityGeneratorEntity<Long> {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long score;

    @PrePersist
    public void prePersist() {
        // Score default value
        if (score == null) score = 0L;
    }
}