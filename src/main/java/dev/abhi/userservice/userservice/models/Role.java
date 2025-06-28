package dev.abhi.userservice.userservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
public class Role extends BaseModel{
    private String roleName;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;
        return Objects.equals(getRoleName(), role1.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getRoleName());
    }
}
