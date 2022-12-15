# aws-main

## lien pour les deux microservices

[aws s3](https://github.com/AMT-Team6-Vogel-Tissot/aws-s3/tree/develop)

[aws rekognition](https://github.com/AMT-Team6-Vogel-Tissot/aws-rekognition/tree/develop)

## structure des liens pour Spring

### POST

 - ajoutObjet: ajout d'un objet dans S3
    - objet
 
### GET 
 
| url          | paramètre     | retour                            | code réussite | code erreur                        |
|--------------|---------------|-----------------------------------|---------------|------------------------------------|
| objet/publie | nom           | url de l'objet                    | 200           | 404 (objet ou bucket non existant) |
| objet/existe | nom           | true ou false                     | 200           | pas d'erreur possible              |
| objets       |               | liste des objets (pleine ou vide) | 200           | 404 (bucket non existant)          |
| objet/{name} | nom dans path | tableau byte                      | 200           | 404 (objet ou bucket no existant)  |
    
 ## chronologie
 
  - création d'un objet dans S3 (main -> S3)
    - réussite ou échec
  - demande l'analyse (main -> rekognition)
    - envoi du résultat ou échec 
 
