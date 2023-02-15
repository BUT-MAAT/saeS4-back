package com.saes4.saes4.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.saes4.saes4.model.enums.TYPE_CATEGORIE;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categorie")
public class Categorie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_categorie;

    private String nom_categorie;

    @Enumerated(EnumType.STRING)
    private TYPE_CATEGORIE type_categorie;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categorie_parent")
    @JsonBackReference
    private Categorie categorie_parent;
}
