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
   id_personne INTEGER PRIMARY KEY AUTO_INCREMENT,
   nom VARCHAR(30),
   prenom VARCHAR(30),
   mail VARCHAR(30),
   code_postal VARCHAR(5),
   ville VARCHAR(30),
   date_reponse DATE,
   CONSTRAINT U_Mail_Sondage UNIQUE(mail)
);

CREATE OR REPLACE TABLE CHOIX_ALIMENTS_SONDAGE (
   id_aliment INTEGER,
   id_personne INTEGER,
   PRIMARY KEY(id_aliment, id_personne),
   FOREIGN KEY(id_aliment) REFERENCES ALIMENT(id_aliment),
   FOREIGN KEY(id_personne) REFERENCES SONDAGE(id_personne)
);


CREATE OR REPLACE TABLE STATISTIQUES_GENERALES (
    id_statistiques INTEGER PRIMARY KEY,
    timelog DATETIME,
    nombre_reponses INTEGER,
    id_aliment_plus_choisi INTEGER,
    nb_selections_aliment INTEGER,
    id_categorie_plus_choisi INTEGER,
    nb_selections_categorie INTEGER,
    CONSTRAINT U_Statistiques_Timelog UNIQUE(timelog),
    CONSTRAINT FK_Statistiques_Aliment
        FOREIGN KEY(id_aliment_plus_choisi) REFERENCES ALIMENT(id_aliment),
    CONSTRAINT FK_Statistiques_Categorie
        FOREIGN KEY(id_categorie_plus_choisi) REFERENCES CATEGORIE(id_categorie)
);

-- Creation des Sequences

CREATE OR REPLACE SEQUENCE SONDAGE_SEQ;
CREATE OR REPLACE SEQUENCE STATISTIQUES_GENERALES_SEQ;

-- Vue qui renvoie la liste des categories les plus choisies triee
CREATE OR REPLACE VIEW V_CategoriesTrieesParSelection
AS
SELECT C.ID_CATEGORIE, COUNT(C.ID_CATEGORIE) AS NB_SELECTIONS
FROM (((CHOIX_ALIMENTS_SONDAGE CAS INNER JOIN ALIMENT A ON CAS.id_aliment=A.id_aliment)
    INNER JOIN CATEGORIE SS_C ON SS_C.id_categorie=A.id_sous_sous_categorie)
    INNER JOIN CATEGORIE S_C ON S_C.id_categorie=SS_C.id_categorie_parent)
    INNER JOIN CATEGORIE C ON C.id_categorie=S_C.id_categorie_parent
WHERE C.TYPE_CATEGORIE LIKE 'CATEGORIE'
GROUP BY C.ID_CATEGORIE
ORDER BY COUNT(C.ID_CATEGORIE) DESC;



-- A activer apres passage a MariaDB
-- CREATE OR REPLACE TRIGGER T_Sondage_Statistiques
-- AFTER INSERT ON SONDAGE AS
-- DECLARE
--     NbReponses INTEGER;
--     IdAlimentPlusChoisi INTEGER;
--     IdCategoriePlusChoisie INTEGER;
-- BEGIN
--     -- On compte le nombre de reponses
--     SELECT COUNT(id_personne) INTO NbReponses FROM SONDAGE;
--     -- Trouve l'aliment le plus choisi
--     SELECT TOP(1) id_aliment INTO IdAlimentPlusChoisi, COUNT(id_aliment)
--     FROM CHOIX_ALIMENTS_SONDAGE
--     GROUP BY id_aliment
--     ORDER BY COUNT(id_aliment);
--     -- Trouve la categorie la plus choisie
--     SELECT TOP(1) id_categorie INTO IdCategoriePlusChoisie FROM V_CategoriesTrieesParSelection;
--     -- On insere le tout dans les statistiques
--     INSERT INTO STATISTIQUES_GENERALES(id_statistiques, timelog, nombre_reponses, id_aliment_plus_choisi, id_categorie_plus_choisi)
--     VALUES(NEXTVALUE FOR STATISTIQUES_GENERALES_SEQ, SYSDATE, NbReponses, IdAlimentPlusChoisi, IdCategoriePlusChoisie);
-- END;
