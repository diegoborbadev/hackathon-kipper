package dev.diegoborba.hackathonkipper.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseModel<ID> implements Serializable {

    public abstract ID getId();

    public abstract void setId(ID id);
}