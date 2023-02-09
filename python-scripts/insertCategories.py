import pandas as pd
import utils as utils

def main():
    with open('generated-sql/insertCategories.sql', 'w', encoding="utf-8") as file:
        excelFile = pd.read_excel('Aliments.xlsx')

        # Delete existing data
        # utils.writeDelimitation(file, "RESET TABLES")
        # file.write("DELETE FROM SOUSSOUSCATEGORIE;\n")
        # file.write("DELETE FROM SOUSCATEGORIE;\n")
        # file.write("DELETE FROM CATEGORIE;\n")

        # Categories
        utils.writeDelimitation(file, "CATEGORIES")
        categories = excelFile.filter(['alim_grp_code', 'alim_grp_nom_fr']).drop_duplicates(subset=['alim_grp_code'])
        for idx in categories.index:
            file.write(f"INSERT INTO CATEGORIE(id_categorie, nom_categorie, type_categorie, id_categorie_parent) "
                       f"VALUES({categories['alim_grp_code'][idx]},"
                       f"'{categories['alim_grp_nom_fr'][idx].capitalize()}',"
                       f"'CATEGORIE',"
                       f"NULL);\n")
        print("[DONE] - Script categories done")

        # Child categories
        utils.writeDelimitation(file, "CHILD CATEGORIES")
        child_categories = excelFile.filter(['alim_ssgrp_code', 'alim_ssgrp_nom_fr']).drop_duplicates(subset=['alim_ssgrp_code'])
        for idx in child_categories.index:
            file.write(f"INSERT INTO CATEGORIE(id_categorie, nom_categorie, type_categorie, id_categorie_parent) "
                       f"VALUES({str(child_categories['alim_ssgrp_code'][idx])},"
                       f"'{child_categories['alim_ssgrp_nom_fr'][idx].capitalize()}',"
                       f"'SOUSCATEGORIE',"
                       f"{str(child_categories['alim_ssgrp_code'][idx])[:-2]});\n")
        print("[DONE] - Script childcategories done")

        # Child-child categories
        utils.writeDelimitation(file, "CHILD CHILD CATEGORIES")
        child_child_categories = excelFile.filter(['alim_ssgrp_code','alim_ssssgrp_code', 'alim_ssssgrp_nom_fr']).drop_duplicates(subset=['alim_ssgrp_code','alim_ssssgrp_code'])
        for idx in child_child_categories.index:
            # Il existe des erreurs dans le fichier original
            if child_child_categories['alim_ssssgrp_code'][idx] == 0 \
            or str(child_child_categories['alim_ssssgrp_code'][idx])[:-2] == str(child_child_categories['alim_ssgrp_code'][idx]):
                file.write(f"INSERT INTO CATEGORIE(id_categorie, nom_categorie, type_categorie, id_categorie_parent) "
                       f"VALUES({ str(child_child_categories['alim_ssgrp_code'][idx]) + '00' if child_child_categories['alim_ssssgrp_code'][idx] == 0 else str(child_child_categories['alim_ssssgrp_code'][idx]) },"
                       f"'{ child_child_categories['alim_ssssgrp_nom_fr'][idx].capitalize() }',"
                       f"'SOUSSOUSCATEGORIE',"
                       f"{ str(child_child_categories['alim_ssgrp_code'][idx]) });\n")
        print("[DONE] - Script childchildcategories done")

main()
