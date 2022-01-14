package com.lyubchenko.springbootapplicationhomework8.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    private final UUID id = UUID.randomUUID();

    @Column(name = "name")
    @NotEmpty(message = "Name cannot be null")
    private String name;

    @OneToMany(mappedBy = "customer", fetch =FetchType.EAGER,
            cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    private Set<Product> products = new HashSet<>();

}
