# Rapport SAE 2.01-2.02

## Instructions

## explication des classes

le développement de ce projet à été divisé en deux parties principales, le code et l'interface.

### Code

il y a 5 classes dans cette partie :

- Person
- Pair
- Plateform
- Critere
- CSVHandler

#### Person

Person est la classe qui s'occupe des personnes et des informations qui leur sont liées comme la vérification du respect des contraintes entre deux personnes, les calculs d'affinités ou le changement de la forme des informations.

Pour cela, nous avons choisi de mettre les information concernant la personne et de rajouter une encapsulation de Critère.

#### Pair

Pair est la classe qui permet l'association de deux personnes, avec en personne 1 l'hôte, et en personne 2, le visiteur.

Pour cela, nous avons opté pour l'utilisation d'une HashMap de 2 personnes.

#### Plateform

Plateform est la classe qui stocke les liste d'étudiants et de paires,et effectue les appariements. Cette classe nous permet de faires des modification sur les listes d'étudiants et de paires avant de les sauvegarder dans des fichier csv.

Pour cela, nous avons choisi de faire 2 ArrayList, une qui s'occupe de la liste des étudiants et l'autre, de la liste des paires.

#### Critere

Critere est la classe qui gère les critères et qui fait en sorte que ces critères soit au bon format pour la classe Person

#### CSVHandler

CSVHandler est la classe qui permet de load ou de save les etudiants et paires depuis et dans des fichiers csv. Les méthodes de cette classe sont faites pour mettre un lien entre la classe Plateform et les fichiers csv.

#### changements au cour du projet

nous avons décidé de repartir de zéro pour cette version du projet, et de repenser/restructurer ce que nous avions fait. nous avons choisi de séparer les Persons en deux catégories, Host et Guest.

## Capture d'écran de l'application finale

![gestion des étudiants](/img/gestion.png)
![gestion des étudiants](/img/appariement.png)
![gestion des étudiants](/img/configuration.png)

## Nos choix de conception

Nous avons fait attention à la lisibilité de notre application avec une couleur de caractère se détachant bien du fond de l'application.
Afin de ne pas surcharger les utilsateurs d'informations, nous avons décider de séparer les différentes parties de l'application dans trois tabs différentes. Cela permet également de fluidifier l'expérience utilisateur.

l'utilisation d'une fenêtre à part lors de l'ajout d'un étudiant permet de voir la table des étudiants et donc d'éviter perdre son temps en essayant de mettre des doublons.

## Contribution de chaques membres

la partie de code à principalement été réalisé pas Adam et la partie de l'interface par Martin.

### Ressources

pour voir le premier design (qui a changé entre temps)  
[lien vers la maquette Figma)](https://www.figma.com/design/ZTCl0GuIiNFMQy3enEJdM5/Prototype_Application_SeaDev?node-id=0-1&p=f&t=96MRWDUvw573MIHx-0#-1)

pour voir la contribution de chacun et de qui viens quel code  
[lien vers le github](https://github.com/potatovitch/dev-clean)

### Les membres du groupe ayant travaillé sur la partie IHM

STIEVENARD Adam
LELEU Benjamin
LECOESTER Martin
