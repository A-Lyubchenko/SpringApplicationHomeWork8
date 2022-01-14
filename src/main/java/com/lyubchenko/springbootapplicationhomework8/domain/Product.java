package com.lyubchenko.springbootapplicationhomework8.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    private final UUID id = UUID.randomUUID();

    @Column(name = "name")
    @NotEmpty(message = "Name cannot be null")
    private String name;

    @Column(name = "price")
    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false,message = "Price must be more than 0")
    @DecimalMax(value = "10000.0000",message = "Price must be less than 1000")
    private Long price;

    @NotNull(message = "Customer cannot be null")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
