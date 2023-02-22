package com.saes4.saes4.model.entities.liaisons;

import com.saes4.saes4.model.entities.Aliment;
import com.saes4.saes4.model.entities.Sondage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "choix_aliments_sondage")
@IdClass(LiaisonPK.class)
public class LiaisonAlimentSondage {
    @Id
    Sondage personne;

    @Id
    Aliment aliment;
}

@Getter
@Setter
@NoArgsConstructor
class LiaisonPK implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_personne")
    Sondage personne;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_aliment")
    Aliment aliment;
}