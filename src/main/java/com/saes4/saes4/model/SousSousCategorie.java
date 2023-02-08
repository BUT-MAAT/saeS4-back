package com.saes4.saes4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "soussouscategorie")
public class SousSousCategorie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_sous_sous_categorie;

    private String nom_sous_sous_categorie;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sous_categorie")
    private SousCategorie categorie_parent;
}
