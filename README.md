# aws-main

## lien pour les deux microservices

[aws s3](https://github.com/AMT-Team6-Vogel-Tissot/aws-s3/tree/develop)

[aws rekognition](https://github.com/AMT-Team6-Vogel-Tissot/aws-rekognition/tree/develop)

## Unirest

[unirest](https://github.com/kong/unirest-java)

Unirest permet de faire des requêtes facilement en Java. Postman propose de traduire automatiquement les requêtes faites en code et c'est comme ça que nous avons découvert cette librairie. Elle est de plus mieux documentée (et à jour) que les autres librairies que nous avons trouvé sur internet.

## structure des liens pour Spring

### POST

| url          | paramètre     | retour                            | code réussite | code erreur                        |
|--------------|---------------|-----------------------------------|---------------|------------------------------------|
| objet        | nom, fichier  | nom de l'objet                    | 200           | 404 ou 409                         |
| analyse      | rekognitionRequest* dans le body| map<string,string>                | 200           | 422 ou 500                         |
| analyse/base64  | rekognitionRequest* dans le body | map<string,string>                | 200           | 422 ou 500                         |


rekognitionRequest: url d'une image, nombre max de lables voulu (facultatif) et le minimum de confidence souhaité (facultatif)
 
### PATCH

| url          | paramètre                             | retour                     | code réussite | code erreur                        |
|--------------|---------------------------------------|----------------------------|---------------|------------------------------------|
| objet        | nom, fichier, nouveau nom (facultatif)| success                    | 200           | 404 ou 409                         |

### GET 
 
| url          | paramètre          | retour                            | code réussite | code erreur                        |
|--------------|--------------------|-----------------------------------|---------------|------------------------------------|
| objet/publie | nom                | url de l'objet                    | 200           | 404 (objet ou bucket non existant) |
| objet/existe | nom                | true ou false                     | 200           | pas d'erreur possible              |
| objets       |                    | liste des objets (pleine ou vide) | 200           | 404 (bucket non existant)          |
| objet/{nom}  | nom dans path      | tableau byte                      | 200           | 404 (objet ou bucket no existant)  |


### DELETE

| url          | paramètre     | retour                            | code réussite | code erreur                        |
|--------------|---------------|-----------------------------------|---------------|------------------------------------|
| objet/{nom}  | nom dans path | success                           | 200           | 404                                |
    
 ## Docker
 
L'objectif est de build les images dans les GitHub actions puis de le push sur la registry. Sur la machine AWS il faudra se connecter à cette registry puis run les deux images. Le main ne sera pas dans un docker pour simplifier son utilisation. Le service NTP sera dans un troisième docker qui pourra être lancé avec la commande:

```
 docker run --name=ntp            \
             --restart=always      \
             --detach              \
             --publish=123:123/udp \
             --env=NTP_SERVERS="0.uk.pool.ntp.org"\
             cturra/ntp
 
```

Il a été choisi de passer le fichier .env en paramètre au moment de lancer le docker run pour les deux microservices. Cette décision a été prise car créer une image avec un .env copié dedans peut être un risque pour la sécurité et le fonctionnement des dockers secrets est difficile à prendre en main.


```

sudo docker run --env-file .env -p 8080:8080 awss3:latest
sudo docker run --env-file .env -p 8081:8081 awsrekognition:latest


```
