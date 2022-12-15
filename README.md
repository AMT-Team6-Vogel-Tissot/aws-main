# aws-main

## lien pour les deux microservices

[aws s3](https://github.com/AMT-Team6-Vogel-Tissot/aws-s3/tree/develop)

[aws rekognition](https://github.com/AMT-Team6-Vogel-Tissot/aws-rekognition/tree/develop)

## structure des liens pour Spring

### POST

| url          | paramètre     | retour                            | code réussite | code erreur                        |
|--------------|---------------|-----------------------------------|---------------|------------------------------------|
| objet        | nom, fichier  | nom de l'objet                    | 200           | 404 ou 409                         |
 
 
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
| analyse      | rekognitionRequest* | map<string,string>                | 200           | 422 ou 500                         |
| analyse/base64  | à décider       | map<string,string>                | 200           | 422 ou 500                         |


* url d'une image, nombre max de lables voulu (facultatif) et le minimum de confidence souhaité (facultatif)

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
