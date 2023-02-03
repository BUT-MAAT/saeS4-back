DROP TABLE SOUSSOUSCATEGORIE;
DROP TABLE SOUSCATEGORIE;
DROP TABLE CATEGORIE;
DROP TABLE VALEURS_NUTRITIVES;
DROP TABLE ALIMENT;

CREATE OR REPLACE TABLE CATEGORIE (
    id_categorie INTEGER PRIMARY KEY,
    nom_categorie VARCHAR(60)
);

CREATE OR REPLACE TABLE SOUSCATEGORIE (
    id_sous_categorie INTEGER PRIMARY KEY,
    id_categorie INTEGER,
    nom_sous_categorie VARCHAR(60),
    CONSTRAINT FK_SousCategorie_Categorie
        FOREIGN KEY (id_categorie) REFERENCES CATEGORIE(id_categorie)
);

CREATE OR REPLACE TABLE SOUSSOUSCATEGORIE (
    id_sous_sous_categorie INTEGER PRIMARY KEY,
    id_sous_categorie INTEGER,
    nom_sous_sous_categorie VARCHAR(60),
    CONSTRAINT FK_SousSousCategorie_SousCategorie
        FOREIGN KEY (id_sous_categorie) REFERENCES SOUSCATEGORIE(id_sous_categorie)
);

-- -1 = traces
CREATE OR REPLACE TABLE VALEURS_NUTRITIVES (
    IdValeursNutritives INTEGER PRIMARY KEY,
    EnergieUE_kj FLOAT,
    EnergieUE_kcal FLOAT,
    EnergieJones_kj FLOAT,
    EnergieJones_kcal FLOAT,
    Eau FLOAT,
    ProteinesJones FLOAT,
    Proteines625 FLOAT,
    Glucides FLOAT,
    Lipides FLOAT,
    Sucres FLOAT,
    Fructose FLOAT,
    Galactose FLOAT,
    Glucose FLOAT,
    Lactose FLOAT,
    Maltose FLOAT,
    Saccharose FLOAT,
    Amidon FLOAT,
    FibresAlimentaires FLOAT,
    PolyolsTotaux FLOAT,
    Cendres FLOAT,
    Alcool FLOAT,
    AcidesOrganiques FLOAT,
    AGSatures FLOAT,
    AGMonoinsatures FLOAT,
    AGPolyinsatures FLOAT,
    AGButriques FLOAT,
    AGCaproique FLOAT,
    AGCaprylique FLOAT,
    AGCaprique FLOAT,
    AGLaurique FLOAT,
    AGMyristique FLOAT,
    AGPalmitique FLOAT,
    AGSteraique FLOAT,
    AGOleique FLOAT,
    AGLinoleique FLOAT,
    AGAlphaLinoleique FLOAT,
    AGArachidonique FLOAT,
    AGEPA FLOAT,
    AGDHA FLOAT,
    Cholesterol FLOAT,
    Sel FLOAT,
    Calcium FLOAT,
    Chlorure FLOAT,
    Cuivre FLOAT,
    Fer FLOAT,
    Iode FLOAT,
    Magnesium FLOAT,
    Manganese FLOAT,
    Phosphore FLOAT,
    Potassium FLOAT,
    Selenium FLOAT,
    Sodium FLOAT,
    Zinc FLOAT,
    Retinol FLOAT,
    BetaCarotene FLOAT,
    VitamineD FLOAT,
    VitamineE FLOAT,
    VitamineK1 FLOAT,
    VitamineK2 FLOAT,
    VitamineC FLOAT,
    VitamineB1 FLOAT,
    VitamineB2 FLOAT,
    VitamineB3 FLOAT,
    VitamineB5 FLOAT
);

CREATE OR REPLACE TABLE ALIMENT (
    id_aliment INTEGER PRIMARY KEY,
    nom_aliment VARCHAR(250),
    id_valeurs_nutritives INTEGER,
    id_sous_sous_categorie INTEGER,
    CONSTRAINT FK_Aliment_ValeursNutritives
        FOREIGN KEY (id_valeurs_nutritives) REFERENCES VALEURS_NUTRITIVES(IdValeursNutritives),
    CONSTRAINT FK_Aliment_Soussouscategorie
        FOREIGN KEY (id_sous_sous_categorie) REFERENCES SOUSSOUSCATEGORIE(id_sous_sous_categorie)
);