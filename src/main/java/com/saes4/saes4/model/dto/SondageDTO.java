package com.saes4.saes4.model.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SondageDTO {
    private Long id_personne;

    private String nom;

    private String prenom;

    private String mail;

    private String code_postal;

    private String ville;

    private Date date_reponse;

    private List<AlimentDTO> liste_aliments;
}
