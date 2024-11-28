package hu.hardcore.FPEweblap.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Timestamp;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(name = "name")
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
