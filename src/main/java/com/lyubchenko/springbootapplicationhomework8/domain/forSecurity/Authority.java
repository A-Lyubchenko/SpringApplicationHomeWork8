package com.lyubchenko.springbootapplicationhomework8.domain.forSecurity;

import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private Roles authority = Roles.user;

    public String getAuthority() {
        return authority.name();
    }

    public Authority(Roles authority) {
        this.authority = authority;
    }

    public enum Roles {
        admin,
        user,
        manager
    }


}
