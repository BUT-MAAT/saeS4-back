package com.saes4.saes4.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "aliment")
public class Aliment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_aliment;

    private String nom_aliment;


    private Long id_sous_sous_categorie;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_valeurs_nutritives")
    private ValeursNutritives valeurs_nutritives;
}
