import requests as requests
import unidecode
import json
import random
import datetime
import sys
FILE_NAME = "generated-sql/insertUsers.sql"
NUMBER_OF_PERSON = 0
JSON_FILE_PATH = "postal.json"
ALIMENT_API_LINK = "http://localhost:9000/api/aliment/all"
DATE = datetime.datetime.now().strftime("%Y-%m-%d")
f = open(JSON_FILE_PATH)
POSTAUX = json.load(f)['postal_code']
f.close()

if(len(sys.argv)< 2 or len(sys.argv)>3):
    raise ValueError("Nombre d'argument invalide : python insertUsers.py <user_number> [yyyy-mm-dd]")
if(sys.argv[1].isdigit()):
    NUMBER_OF_PERSON = int(sys.argv[1])
else:
    raise ValueError("Le premier argument doit être un nomber (nombre d'utilisateurs souhaité)")
if(len(sys.argv) == 3):
    DATE = sys.argv[2]
    try:
        date_obj = datetime.datetime.strptime(DATE, "%Y-%m-%d")
    except ValueError:
        print("String does not match date format")
        raise ValueError("Le deuxième argument en respecte pas le format de la date ( yyyy-mm-dd )")



def getRandomAliment(food, returnSize):
    aliments = []
    number = len(food)
    return random.sample(food,returnSize)


def generateSurveySQL(firstname,lastname,email,address,city,postal,aliments,date,file):
    surveyInsert = (f"INSERT INTO SONDAGE (nom,prenom,mail,code_postal,ville,date_reponse)"
                    f" VALUES ('{firstname}','{lastname}','{email}','{postal}','{city}','{date}');\n")
    file.write(surveyInsert)
    for aliment in aliments:
        responseInsert = f"INSERT INTO CHOIX_ALIMENTS_SONDAGE (id_personne,id_aliment) VALUES ((SELECT MAX(id_personne) FROM SONDAGE) ,{aliment});\n"
        file.write(responseInsert)
    file.write("\n")

def main():
    r = requests.get(url=ALIMENT_API_LINK)
    food = r.json()
    print(type(food))
    for item in food:
        del item["nom_aliment"]
        del item["id_sous_sous_categorie"]
        del item["valeurs_nutritives"]
    with open(FILE_NAME, "w") as f:
        for i in range(NUMBER_OF_PERSON):
            print(f"{i}/{NUMBER_OF_PERSON}")
            # Requesting french-sounding names through an API
            r = requests.get(url = "https://api.namefake.com/french-france/")

            person = r.json()
            name = person['name'].split()
            firstname = name[0]
            lastname = name[1]
            email = unidecode.unidecode(f"{firstname}.{lastname}@gmail.com".lower())
            location = person['address'].split("\n")
            postal = POSTAUX[random.randint(0,len(POSTAUX)-1)]
            city = location[1].split()[-1]
            address = location[0].replace(',',"")
            aliments = getRandomAliment(food,10)
            generateSurveySQL(firstname,lastname,email,address,city,postal,aliments, DATE,f)


main()