# coding=utf8

import pandas as pd
import utils as utils

QUOTATION_MARK = "'"

def convertFloat(value: str):
    if value == 'traces':
        return -1
    if value == '-' or value == 'nan':
        return 'NULL'
    if value.startswith(">") or value.startswith("<"):
        return float(value[2:].replace(",", "."))
    return float(value.replace(",", "."))

def getChildChildCategorie(excelFile, idx):
    return str(excelFile['alim_grp_code'][idx]) \
            + str(excelFile['alim_ssgrp_code'][idx])[-2:] \
            + (str(excelFile['alim_ssssgrp_code'][idx])[-2:]
                if excelFile['alim_ssssgrp_code'][idx] != 0
                else '00')

def main():
    with open('generated-sql/insertAliments.sql', 'w', encoding="utf-8") as file:
        excelFile = pd.read_excel('Aliments.xlsx')

        # Delete existing data
        # utils.writeDelimitation(file, "RESET TABLES")
        # file.write("DELETE FROM ALIMENT;\n")
        # file.write("DELETE FROM VALEURS_NUTRITIVES;\n")

        # We insert data in valeurs nutritives then in aliments
        utils.writeDelimitation(file, "VALEURS_NUTRITIVES -> ALIMENT")
        for idx in excelFile.index:
            print(excelFile['alim_code'][idx])
            utils.writeSmallDelimitation(file, f"{excelFile['alim_code'][idx]} - {excelFile['alim_nom_fr'][idx]}");
            file.write(f"INSERT INTO valeurs_nutritives("
                       f"id_valeurs_nutritives, energie_ue_kj, energie_ue_kcal, energie_jones_kj, energie_jones_kcal, "
                       f"eau, proteines_jones, proteines_625, glucides, lipides, sucres, fructose, galactose, "
                       f"glucose, lactose, maltose, saccharose, amidon, fibres_alimentaires, polyols_totaux, cendres, "
                       f"alcool, acides_organiques, ag_satures, ag_monoinsatures, ag_polyinsatures, ag_butriques, "
                       f"ag_caproique, ag_caprylique, ag_caprique, ag_laurique, ag_myristique, ag_palmitique, ag_steraique, "
                       f"ag_oleique, ag_linoleique, ag_alpha_linoleique, ag_arachidonique, ag_epa, ag_dha, cholesterol, "
                       f"sel, calcium, chlorure, cuivre, fer, iode, magnesium, manganese, phosphore, potassium, "
                       f"selenium, sodium, zinc, retinol, beta_carotene, vitamine_d, vitamine_e, vitamine_k1, "
                       f"vitamine_k2, vitamine_c, vitamine_b1, vitamine_b2, vitamine_b3, vitamine_b5) "
                       f"VALUES({excelFile['alim_code'][idx]}, "
                       f"{convertFloat(str(excelFile['Energie, Règlement UE N° 1169/2011 (kJ/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Energie, Règlement UE N° 1169/2011 (kcal/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Energie, N x facteur Jones, avec fibres  (kJ/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Energie, N x facteur Jones, avec fibres  (kcal/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Eau (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Protéines, N x facteur de Jones (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Protéines, N x 625 (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Glucides (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Lipides (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Sucres (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Fructose (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Galactose (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Glucose (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Lactose (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Maltose (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Saccharose (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Amidon (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Fibres alimentaires (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Polyols totaux (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Cendres (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Alcool (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Acides organiques (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG saturés (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG monoinsaturés (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG polyinsaturés (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 4:0, butyrique (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 6:0, caproïque (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 8:0, caprylique (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 10:0, caprique (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 12:0, laurique (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 14:0, myristique (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 16:0, palmitique (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 18:0, stéarique (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 18:1 9c (n-9), oléique (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 18:2 9c,12c (n-6), linoléique (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 18:3 c9,c12,c15 (n-3), alpha-linolénique (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 20:4 5c,8c,11c,14c (n-6), arachidonique (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 20:5 5c,8c,11c,14c,17c (n-3) EPA (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['AG 22:6 4c,7c,10c,13c,16c,19c (n-3) DHA (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Cholestérol (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Sel chlorure de sodium (g/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Calcium (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Chlorure (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Cuivre (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Fer (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Iode (µg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Magnésium (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Manganèse (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Phosphore (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Potassium (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Sélénium (µg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Sodium (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Zinc (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Rétinol (µg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Beta-Carotène (µg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Vitamine D (µg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Vitamine E (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Vitamine K1 (µg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Vitamine K2 (µg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Vitamine C (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Vitamine B1 ou Thiamine (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Vitamine B2 ou Riboflavine (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Vitamine B3 ou PP ou Niacine (mg/100 g)'][idx]))}, "
                       f"{convertFloat(str(excelFile['Vitamine B5 ou Acide pantothénique (mg/100 g)'][idx]))}"
                       f");\n")

            file.write(f"INSERT INTO ALIMENT("
                       f"id_aliment, nom_aliment, id_valeurs_nutritives, id_sous_sous_categorie)"
                       f"VALUES({excelFile['alim_code'][idx]}, "
                       f"{QUOTATION_MARK}{excelFile['alim_nom_fr'][idx].replace(QUOTATION_MARK,' ')}{QUOTATION_MARK}, "
                       f"{excelFile['alim_code'][idx]}, "
                       f"{getChildChildCategorie(excelFile, idx)}"
                       f");\n")


main()