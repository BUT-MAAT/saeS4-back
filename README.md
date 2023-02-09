# saeS4-back - Documentation

---

Vous pouvez trouver ici le manuel d'utilisation de notre API 

Notre application est accessible via le port `9000`
> Vous pouvez utiliser l'url suivante pour accèder à l'api :
> http://localhost:9000/api

---
## Categories

Endpoint global : `/categories`

> Il existe 3 types de catégories : 
> - Catégories (CATEGORIE)
> - Sous catégories (SOUSCATEGORIE)
> - Sous sous catégories (SOUSSOUSCATEGORIE)
> 
> Les sous sous catégories sont une sous catégorie des sous catégories qui elles 
> même sont une sous catégorie de catégorie.


- ### Get all categories (CATEGORIE) : [/categorie/all](http://localhost:9000/api/categories/categorie/all)

Paramètres : `aucun`

Renvoie toutes les catégories (CATEGORIE) dans la base :
```json
[
  {
    "id_categorie": 1,
    "nom_categorie": "Entrées et plats composés",
    "type_categorie": "CATEGORIE"
  }
]
```
> Exemple d'appel : http://localhost:9000/api/categories/categorie/all


- ### Get categories by parent : [/by_parent](http://localhost:9000/api/categories/by_parent?parent_id=1)

Paramètre : `parent_id : number`

Renvoie toutes les catégories dont la catégorie parent est celle dont l'id a été passé en paramètre :
```json
[
  {
    "id_categorie":101,
    "nom_categorie":"Salades composées et crudités",
    "type_categorie":"SOUSCATEGORIE"
  }
]
```
> Exemple d'appel : http://localhost:9000/api/categories/by_parent?parent_id=1


---
## Aliments

Endpoint global : `/aliment`


- ### **Get all :** [/all](http://localhost:9000/api/aliment/all)

Paramètres : `aucun`

Renvoie tous les aliments en base en suivant le format suivant :
```json
[
  {
    "id_aliment": 1000,
    "nom_aliment": "Pastis",
    "id_sous_sous_categorie": 60303
  }
]
```
> Exemple d'appel : http://localhost:9000/api/aliment/all


- ### **Get by sous sous categorie :** [/by_soussouscategorie](http://localhost:9000/api/aliment/by_soussouscategorie?soussouscategorie_id=10100)

Paramètres : `soussouscategorie_id : number`

Renvoie les aliments en base qui sont de la sous sous categorie dont l'id a été passé en paramètre :
```json
[
  {
    "id_aliment": 25601,
    "nom_aliment": "Salade de thon et légumes, appertisée",
    "id_sous_sous_categorie": 10100
  }
]
```
> Exemple d'appel : http://localhost:9000/api/aliment/by_soussouscategorie?soussouscategorie_id=10100


- ### **Get valeurs nutrionelles d'un aliment :** [/valeurs_nutritives](http://localhost:9000/api/aliment/valeurs_nutritives?aliment_id=1000)

Paramètres : `aliment_id" : number`

Renvoie les valeurs nutritionnelles de l'aliment dont l'id a été passé en paramètre :

```json
{
  "id_valeurs_nutritives":  1000,
  "energie_ue_kj": 1140.0,
  "energie_ue_kcal": 274.0,
  "energie_jones_kj": 1140.0,
  "energie_jones_kcal": 274.0,
  "eau": 59.7,
  "proteines_Jones": 0.0,
  "proteines_625": 0.0,
  "glucides": 2.86,
  "lipides": 0.0,
  "sucres": 0.0,
  "fructose": null,
  "galactose": null,
  "glucose": null,
  "lactose": null,
  "maltose": null,
  "saccharose": null,
  "amidon": 0.0,
  "fibres_alimentaires": 0.0,
  "polyols_totaux": 0.0,
  "cendres": 0.002,
  "alcool": 37.5,
  "acides_organiques": 0.0,
  "ag_satures": 0.0,
  "ag_monoinsatures": 0.0,
  "ag_polyinsatures": 0.0,
  "ag_butriques": null,
  "ag_caproique": null,
  "ag_caprylique": null,
  "ag_caprique": null,
  "ag_laurique": null,
  "ag_myristique": null,
  "ag_palmitique": null,
  "ag_steraique": null,
  "ag_oleique": null,
  "ag_linoleique": null,
  "ag_alpha_linoleique": null,
  "ag_arachidonique": null,
  "ag_epa": null,
  "ag_dha": null,
  "cholesterol": 0.0,
  "sel": 0.0,
  "calcium": 0.0,
  "chlorure": null,
  "cuivre": null,
  "fer": null,
  "iode": 1.0,
  "magnesium": 0.0,
  "manganese": 0.0,
  "phosphore": null,
  "potassium": 2.0,
  "selenium": 0.0,
  "sodium": 0.0,
  "zinc": 0.02,
  "retinol": 0.0,
  "beta_carotene": 0.0,
  "vitamine_D": 0.0,
  "vitamine_E": null,
  "vitamine_K1": null,
  "vitamine_K2": null,
  "vitamine_C": 0.0,
  "vitamine_B1": 0.0,
  "vitamine_B2": 0.0,
  "vitamine_b3": 0.0,
  "vitamine_b5": 0.0
}
```
**ATTENTION :** *Si la valeur retournée pour un champ vaut `-1`, alors c'est qu'il existe des traces de ce nutriment*

> Exemple d'appel : http://localhost:9000/api/aliment/valeurs_nutritives?aliment_id=1000
