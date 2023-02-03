package com.saes4.saes4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Categorie {
    @Id
    Long idCategorie;
}
