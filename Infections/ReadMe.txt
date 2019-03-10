#Infection
---

##Membre du groupe
    - Aubry Nicolas 21603763 Informatique
    - Hu Eric 21713256 Informatique

Groupe 4 avec Gregory Bonnet

##Compilation

Compiler tous les dossiers sources avec cette commande bash:
#bash
```
javac -d build src/*/*.java
```
Si vous êtes sur windows et que cette commande ne marche pas, il faudra alors utiliser:
```
javac -d build src/player/*.java src/move/*.java src/state/*.java src/test/*.java
```

##Execution
Notre jeu s'execute avec 6 paramètres:
— la longueur et la largeur de la grille,
— le nombre de coup d’avance donné au joueur blanc,
— la profondeur de raisonnement du joueur blanc,
— la profondeur de raisonnement du joueur noir,
— l’utilisation ou non d’un élagage alphabeta.
Ces paramètres sont tous des entiers. Les deux premiers paramètres doivent être supérieurs à 1, où bien le jeu n'a pas vraiment d'intérêt. Même si une grille de 2,2 ne présente pas un grand intérêt non plus, nous considérons qu'il est possible de jouer sur ce genre de grille.
Pour le dernier paramètre, 0 sera pour l'absence d'élaguage et 1 pour le contraire. Le joueur alphaBeta est dans notre jeu le deuxième joueur. Chaque joueur est par défaut un NégaMax.
Voici un exemple d'execution de la commande:
```
java -cp build test.Test 5 3 2 5 5 0
```
