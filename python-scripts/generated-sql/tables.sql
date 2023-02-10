DROP TABLE CATEGORIE;
DROP TABLE VALEURS_NUTRITIVES;
DROP TABLE ALIMENT;
DROP TABLE CHOIX_ALIMENTS_SONDAGE;
DROP TABLE SONDAGE;

CREATE OR REPLACE TABLE CATEGORIE (
    id_categorie INTEGER PRIMARY KEY,
    nom_categorie VARCHAR(60),
    type_categorie VARCHAR(17),
    id_categorie_parent INTEGER,
    CONSTRAINT FK_Categorie_CategorieParent
        FOREIGN KEY (id_categorie_parent) REFERENCES CATEGORIE(id_categorie)
);

-- -1 = traces
CREATE OR REPLACE TABLE VALEURS_NUTRITIVES (
    id_valeurs_nutritives INTEGER PRIMARY KEY,
    energie_ue_kj FLOAT,
    energie_ue_kcal FLOAT,
    energie_jones_kj FLOAT,
    energie_jones_kcal FLOAT,
    eau FLOAT,
    proteines_Jones FLOAT,
    proteines_625 FLOAT,
    glucides FLOAT,
    lipides FLOAT,
    sucres FLOAT,
    fructose FLOAT,
    galactose FLOAT,
    glucose FLOAT,
    lactose FLOAT,
    maltose FLOAT,
    saccharose FLOAT,
    amidon FLOAT,
    fibres_alimentaires FLOAT,
    polyols_totaux FLOAT,
    cendres FLOAT,
    alcool FLOAT,
    acides_organiques FLOAT,
    ag_satures FLOAT,
    ag_monoinsatures FLOAT,
    ag_polyinsatures FLOAT,
    ag_butriques FLOAT,
    ag_caproique FLOAT,
    ag_caprylique FLOAT,
    ag_caprique FLOAT,
    ag_laurique FLOAT,
    ag_myristique FLOAT,
    ag_palmitique FLOAT,
    ag_steraique FLOAT,
    ag_oleique FLOAT,
    ag_linoleique FLOAT,
    ag_alpha_linoleique FLOAT,
    ag_arachidonique FLOAT,
    ag_epa FLOAT,
    ag_dha FLOAT,
    cholesterol FLOAT,
    sel FLOAT,
    calcium FLOAT,
    chlorure FLOAT,
    cuivre FLOAT,
    fer FLOAT,
    iode FLOAT,
    magnesium FLOAT,
    manganese FLOAT,
    phosphore FLOAT,
    potassium FLOAT,
    selenium FLOAT,
    sodium FLOAT,
    zinc FLOAT,
    retinol FLOAT,
    beta_carotene FLOAT,
    vitamine_D FLOAT,
    vitamine_E FLOAT,
    vitamine_K1 FLOAT,
    vitamine_K2 FLOAT,
    vitamine_C FLOAT,
    vitamine_B1 FLOAT,
    vitamine_B2 FLOAT,
    vitamine_b3 FLOAT,
    vitamine_b5 FLOAT
);

CREATE OR REPLACE TABLE ALIMENT (
    id_aliment INTEGER PRIMARY KEY,
    nom_aliment VARCHAR(250),
    id_valeurs_nutritives INTEGER,
    id_sous_sous_categorie INTEGER,
    CONSTRAINT FK_Aliment_ValeursNutritives
        FOREIGN KEY (id_valeurs_nutritives) REFERENCES VALEURS_NUTRITIVES(id_valeurs_nutritives),
    CONSTRAINT FK_Aliment_Soussouscategorie
        FOREIGN KEY (id_sous_sous_categorie) REFERENCES CATEGORIE(id_categorie)
);

CREATE OR REPLACE TABLE SONDAGE (
   id_personne INTEGER PRIMARY KEY,
   nom VARCHAR(50),
   prenom VARCHAR(50),
   mail VARCHAR(50),
   CONSTRAINT U_Mail_Sondage UNIQUE(mail)
);

CREATE TABLE CHOIX_ALIMENTS_SONDAGE (
   id_aliment INTEGER,
   id_personne INTEGER,
   PRIMARY KEY(id_aliment, id_personne),
   FOREIGN KEY(id_aliment) REFERENCES ALIMENT(id_aliment),
   FOREIGN KEY(id_personne) REFERENCES SONDAGE(id_personne)
);