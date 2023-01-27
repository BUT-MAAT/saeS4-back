import pandas as pd

def writeDelimitation(file, text):
    file.write(f"-- --------------------------------------------------------\n-- ")
    for i in range((56 - (len(text) + 2)) // 2):
        file.write("-")
    file.write(f" {text} ")
    for i in range((56 - (len(text) + 2)) // 2):
        file.write("-")
    file.write("\n")
    file.write(f"-- --------------------------------------------------------\n")

def main():
    with open('generated-sql/insertCategories.sql', 'w') as file:
        excelFile = pd.read_excel('Aliments.xlsx')

        # Delete existing data
        writeDelimitation(file, "RESET TABLES")
        file.write("DELETE FROM SOUSSOUSCATEGORIE;\n")
        file.write("DELETE FROM SOUSCATEGORIE;\n")
        file.write("DELETE FROM CATEGORIE;\n")

        # Categories
        writeDelimitation(file, "CATEGORIES")
        categories = excelFile.filter(['alim_grp_code', 'alim_grp_nom_fr']).drop_duplicates(subset=['alim_grp_code'])
        for idx in categories.index:
            file.write(f"INSERT INTO CATEGORIE(IdCategorie, NomCategorie) "
                       f"VALUES({categories['alim_grp_code'][idx]},"
                       f"'{categories['alim_grp_nom_fr'][idx]}');\n")
        print("[DONE] - Script categories done")

        # Child categories
        writeDelimitation(file, "CHILD CATEGORIES")
        print(type(excelFile.filter(['alim_ssgrp_code', 'alim_ssgrp_nom_fr'])))
        child_categories = excelFile.filter(['alim_ssgrp_code', 'alim_ssgrp_nom_fr']).drop_duplicates(subset=['alim_ssgrp_code'])
        for idx in child_categories.index:
            file.write(f"INSERT INTO SOUSCATEGORIE(IdCategorie, IdSousCategorie, NomSousCategorie) "
                       f"VALUES({str(child_categories['alim_ssgrp_code'][idx])[:-2]},"
                       f"{str(child_categories['alim_ssgrp_code'][idx])},"
                       f"'{child_categories['alim_ssgrp_nom_fr'][idx]}');\n")
        print("[DONE] - Script childcategories done")

        # Child-child categories
        writeDelimitation(file, "CHILD CHILD CATEGORIES")
        child_child_categories = excelFile.filter(['alim_ssgrp_code','alim_ssssgrp_code', 'alim_ssssgrp_nom_fr']).drop_duplicates(subset=['alim_ssssgrp_code'])
        for idx in child_child_categories.index:
            file.write(f"INSERT INTO SOUSSOUSCATEGORIE(IdSousCategorie, IdSousSousCategorie, NomSousSousCategorie) "
                       f"VALUES({str(child_child_categories['alim_ssgrp_code'][idx])},"
                       f"{ str(child_child_categories['alim_ssgrp_code'][idx]) + '00' if child_child_categories['alim_ssssgrp_code'][idx] == 0 else str(child_child_categories['alim_ssssgrp_code'][idx])},"
                       f"'{child_child_categories['alim_ssssgrp_nom_fr'][idx]}');\n")
        print("[DONE] - Script childchildcategories done")

main()