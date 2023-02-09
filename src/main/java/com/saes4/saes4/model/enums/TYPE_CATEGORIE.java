package com.saes4.saes4.model.enums;

public enum TYPE_CATEGORIE {
    CATEGORIE("CATEGORIE"),
    SOUSCATEGORIE("SOUSCATEGORIE"),
    SOUSSOUSCATEGORIE("SOUSSOUSCATEGORIE");

    public final String label;

    private TYPE_CATEGORIE(String label) {
        this.label = label;
    }
}
