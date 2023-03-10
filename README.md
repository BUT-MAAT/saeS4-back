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


- ### Get categories by parent : [/by_parent](http://localhost:9000/api/categories/by_parent/1)

Paramètre : `parent_id : number`

Renvoie toutes les catégories dont la catégorie parent est celle dont l'id a été passé dans l'url :
```json
[
  {
    "id_categorie": 101,
    "nom_categorie": "Salades composées et crudités",
    "type_categorie": "SOUSCATEGORIE"
  }
]
```
> Exemple d'appel : http://localhost:9000/api/categories/by_parent/1


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


- ### **Get by sous sous categorie :** [/by_soussouscategorie](http://localhost:9000/api/aliment/by_soussouscategorie/10100?valeurs_nutritives=false)

Paramètres : `valeurs_nutritives : boolean`

Renvoie les aliments en base qui sont de la sous sous categorie dont l'id a été passé dans l'url.
On peut sélectionner si on souhaite voir ou non les valeurs nutritives des alimenrs:
```json
[
  {
    "id_aliment": 25601,
    "nom_aliment": "Salade de thon et légumes, appertisée",
    "id_sous_sous_categorie": 10100,
    "valeurs_nutritives": null
  }
]
```
> Exemple d'appel : http://localhost:9000/api/aliment/by_soussouscategorie/10100?valeurs_nutritives=false
---

## Address

Endpoint global : `/address`


- ### **Get Address :** [/getaddress](http://localhost:9000/api/address/getaddress/1+Avenue)


Paramètres : `substring : String`

Renvoie une liste de 5 adresse (maximum) qui commence par la substring, si l'api ne trouve pas de résultat elle renvoie les résultats 
pour "1+avenue" si la substring est trop petite (inférieur à 3 sans compter les plus) on rajout automatiquement +Avenue:
```json
[
  {
    "city": "Paris",
    "postcode": "75016",
    "street": "Avenue de Versailles",
    "housenumber": 147
  },
  {
    "city": "Gagny",
    "postcode": "93220",
    "street": "Avenue de Versailles",
    "housenumber": 147
  },
  {
    "city": "Villepreux",
    "postcode": "78450",
    "street": "Avenue de Versailles",
    "housenumber": 147
  },
  {
    "city": "Rueil-Malmaison",
    "postcode": "92500",
    "street": "Avenue de Versailles",
    "housenumber": 0
  },
  {
    "city": "Thiais",
    "postcode": "94320",
    "street": "Avenue de Versailles",
    "housenumber": 0
  }
]

```
> Exemple d'appel : http://localhost:9000/api/address/getaddress/147+Avenue+De+Versailles
---

```
