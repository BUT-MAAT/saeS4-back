package com.saes4.saes4.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "sondage")
public class Sondage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sondage_generator")
    @SequenceGenerator(name = "sondage_generator", sequenceName = "sondage_seq", allocationSize = 1)
    @Column(unique = true, nullable = false)
    private Long id_personne;

    private String nom;

    private String prenom;

    @Column(unique = true)
    private String mail;

    private String code_postal;

    private String ville;

    private Date date_reponse;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "CHOIX_ALIMENTS_SONDAGE",
            joinColumns = { @JoinColumn(name = "id_personne") },
            inverseJoinColumns = { @JoinColumn(name = "id_aliment") })
    private List<Aliment> liste_aliments;
}
