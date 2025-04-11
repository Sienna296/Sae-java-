# Sae-java-
Equipe :
Elodie DAI
Sienna PLISSART
Bassirou DOUCOURE
Tarik MOHAMED

Suivi :
1. Création des Classes de Base :

    Arete : Cette classe représente une arête dans le graphe, qui relie deux sommets. Elle contient :

        Le sommet de départ (dep).

        Le sommet voisin (vois).

        Le poids de l'arête (val). Pourquoi ? Pour modéliser les relations entre les sommets avec un coût associé à chaque connexion.

    T : Représente un sommet avec deux coordonnées (x et y). Pourquoi ? Chaque sommet a besoin de coordonnées pour savoir où il se trouve dans le graphe.

2. Algorithme de Dijkstra :

    Classe dijkstra : Cette classe implémente l’algorithme de Dijkstra pour trouver le plus court chemin entre un point de départ et un point d'arrivée.

        Utilisation d'une file de priorité et d'une map pour suivre les distances.

        Vérification des coordonnées de départ et d'arrivée pour s'assurer qu'elles sont valides.

        Exploration des voisins d’un sommet et mise à jour des distances. Pourquoi ? L'algorithme de Dijkstra est utilisé pour explorer le graphe de manière optimale en trouvant le chemin le plus court.

3. Implémentation du Graphe :

    Classe Implementation : Cette classe gère la structure du graphe avec une HashMap qui associe chaque sommet à une liste de ses voisins (arcs).

        Méthodes pour ajouter un sommet et ajouter un arc.

        Méthode getSucc() pour obtenir les voisins d’un sommet. Pourquoi ? Cela permet de créer et de manipuler le graphe de manière flexible, en ajoutant des sommets et des arcs facilement.

4. Méthodes Utiles :

    getSucc() : Renvoie les voisins d'un sommet donné (par exemple, pour un labyrinthe, cela permet de voir qui sont les voisins dans les directions haut, bas, gauche, droite).


5. Améliorations à Faire :

    Compléter l'algorithme de Dijkstra : Il manque encore des étapes pour calculer correctement les distances et reconstruire le chemin à la fin de l'algorithme.

    Finaliser la gestion des voisins dans la classe dijkstra pour que l’algorithme fonctionne correctement avec la structure du graphe.


Synthèse :

