DROP TABLE SOUSSOUSCATEGORIE;
DROP TABLE SOUSCATEGORIE;
DROP TABLE CATEGORIE;
DROP TABLE VALEURS_NUTRITIVES;
DROP TABLE ALIMENT;

CREATE OR REPLACE TABLE CATEGORIE (
    IdCategorie INTEGER PRIMARY KEY,
    NomCategorie VARCHAR(30)
);

CREATE OR REPLACE TABLE SOUSCATEGORIE (
    IdSousCategorie INTEGER PRIMARY KEY,
    IdCategorie INTEGER,
    NomSousCategorie VARCHAR(30),
    CONSTRAINT FK_SousCategorie_Categorie
        FOREIGN KEY (IdCategorie) REFERENCES CATEGORIE(IdCategorie)
);

CREATE OR REPLACE TABLE SOUSSOUSCATEGORIE (
    IdSousSousCategorie INTEGER PRIMARY KEY,
    IdSousCategorie INTEGER,
    NomSousSousCategorie VARCHAR(30),
    CONSTRAINT FK_SousSousCategorie_SousCategorie
        FOREIGN KEY (IdSousCategorie) REFERENCES SOUSCATEGORIE(IdSousCategorie)
);

CREATE OR REPLACE TABLE ALIMENT (
    IdAliment INTEGER PRIMARY KEY,
    NomAliment VARCHAR(30),
    IdValeursNutritives INTEGER,
    IdSousSousCategorie INTEGER,
    CONSTRAINT FK_Aliment_ValeursNutritives
        FOREIGN KEY (IdValeursNutritives) REFERENCES VALEURS_NUTRITIVES(IdValeursNutritives),
    CONSTRAINT FK_Aliment_Soussouscategorie
        FOREIGN KEY (IdSousSousCategorie) REFERENCES SOUSSOUSCATEGORIE(IdSousSousCategorie)
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