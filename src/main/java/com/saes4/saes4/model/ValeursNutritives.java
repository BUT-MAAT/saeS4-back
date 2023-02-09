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
@Table(name = "valeurs_nutritives")
public class ValeursNutritives implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKeyJoinColumn(name = "id_valeurs_nutritives")
    private Long id_valeurs_nutritives;

    private double energie_ue_kj;
    private double energie_ue_kcal;
    private double energie_jones_kj;
    private double energie_jones_kcal;
    private double eau;
    private double proteines_Jones;
    private double proteines_625;
    private double glucides;
    private double lipides;
    private double sucres;
    private double fructose;
    private double galactose;
    private double glucose;
    private double lactose;
    private double maltose;
    private double saccharose;
    private double amidon;
    private double fibres_alimentaires;
    private double polyols_totaux;
    private double cendres;
    private double alcool;
    private double acides_organiques;
    private double ag_satures;
    private double ag_monoinsatures;
    private double ag_polyinsatures;
    private double ag_butriques;
    private double ag_caproique;
    private double ag_caprylique;
    private double ag_caprique;
    private double ag_laurique;
    private double ag_myristique;
    private double ag_palmitique;
    private double ag_steraique;
    private double ag_oleique;
    private double ag_linoleique;
    private double ag_alpha_linoleique;
    private double ag_arachidonique;
    private double ag_epa;
    private double ag_dha;
    private double cholesterol;
    private double sel;
    private double calcium;
    private double chlorure;
    private double cuivre;
    private double fer;
    private double iode;
    private double magnesium;
    private double manganese;
    private double phosphore;
    private double potassium;
    private double selenium;
    private double sodium;
    private double zinc;
    private double retinol;
    private double beta_carotene;
    private double vitamine_D;
    private double vitamine_E;
    private double vitamine_K1;
    private double vitamine_K2;
    private double vitamine_C;
    private double vitamine_B1;
    private double vitamine_B2;
    private double vitamine_b3;
    private double vitamine_b5;
}
