# aws-main

## lien pour les deux microservices

[aws s3](https://github.com/AMT-Team6-Vogel-Tissot/aws-s3/tree/develop)

[aws rekognition](https://github.com/AMT-Team6-Vogel-Tissot/aws-rekognition/tree/develop)

## structure des liens pour Spring

### POST

 - ajoutObjet: ajout d'un objet dans S3
    - objet
 
### GET 
 
 - creationBucket: création d'un nouveau bucket
    - nom 
 - demandeObjet: demande l'URL s3 pour un objet
    - nom 
 - analyse: demande l'analyse d'une image
    - nom
    - maxLabels
    - minConfidence
    
 ## chronologie
 
  - création d'un objet dans S3 (main -> S3)
    - réussite ou échec
  - demande l'analyse (main -> rekognition)
    - envoi du résultat ou échec (rekognition -> main)
 
