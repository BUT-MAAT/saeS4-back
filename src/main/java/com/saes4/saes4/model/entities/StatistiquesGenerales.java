package com.saes4.saes4.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "statistiques_generales")
public class StatistiquesGenerales implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statistiques_generales_generator")
    @SequenceGenerator(name = "statistiques_generales_generator", sequenceName = "statistiques_generales_seq", allocationSize = 1)
    @Column(unique = true, nullable = false)
    private Long id_statistiques;

    private Date timelog;

    private Long nombre_reponses;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_aliment_plus_choisi")
    private Aliment aliment_plus_choisi;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_categorie_plus_choisi")
    private Categorie categorie_plus_choisi;
}
