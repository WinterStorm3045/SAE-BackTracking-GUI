package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grille {
    private Integer[] grilleDepart;
    private Integer[] grilleJoueur;
    private float difficulte;

    public Grille(float difficulte){
        grilleDepart = new Integer[81]; // liste de 81 Integer (indicé de 0 à 80) pour la liste de numéros de la grille
        this.difficulte = difficulte; // pour déterminer si il y aura un numéro dans une case

        grilleDepart = genererGrille(0, new Integer[81]); // cf genererGrille
        // on commence à l'indice 0 pour former la grillede manière complète

        System.out.println("Grille départ : " + Arrays.toString(grilleDepart));

        grilleJoueur = grilleDepart.clone(); // on récupère la grille de départ
        for (int i=0; i<81; i++){ // pour chaque i de 0 à 81 exclus
            float test = (float)Math.random(); // on tire au sort un float avec randint
            if (test < difficulte){// Si ce float est inférieur à la difficulté (d'où l'utilité du champ)
                grilleJoueur[i] = null; // on met null à l'indice i courant
            }
        }
        System.out.println("Grille joueur : " + Arrays.toString(grilleJoueur)); // on affiche la liste
    }


    private Integer[] genererGrille(int index, Integer[] grille) {
        // index : nécessaire pour former la grille de départ afin de donner un numéro
        // à chaque case
        // Integer[] grille : la grille qu'on va remplir
        if (index == 81) { return grille; }
        // Condition d'arrêt : si index==81, on renvoie la grille, càd qu'on a bien complété la grille
        // On prépare une liste d'entiers et on va les mélanger au hasard
        List<Integer> nombres = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        java.util.Collections.shuffle(nombres);

        for (Integer nbAleatoire : nombres) {
            // pour chaque nb aléatoire
            if (verifierConditions(index, nbAleatoire, grille)) {
                // si les conditions sont vérifiées
                Integer[] nouvelleGrille = grille.clone(); // on clone la grille
                nouvelleGrille[index] = nbAleatoire; // on met le nb aléatoire à l'indice index
                // Appel récursif : on prépare la 2è case qui sera remplie, et on donne en param la
                // nouvelle grille produite pour sauvegarder insertions de numéros précédentes
                Integer[] resultat = genererGrille(index + 1, nouvelleGrille);

                if (resultat != null) { return resultat; }
            }
        }
        return null;
    }



    public boolean verifierConditions(int i, Integer nbAleatoire, Integer[] grille){
        // Boucler sur la colone
        int colone = i % 9;
        // colonne pour chaque case respective, d'où l'utilisation du modulo
        for (int y=0; y<9; y++){
            Integer valeur = getCase(grille, colone, y); // On récupère la case à la position colonne/y
            // les 2 arguments colonne et y sont nécessaires pour bien déterminer l'indice de la valeur qu'on va récup dans la grille
            if (valeur != null && valeur.equals(nbAleatoire)) return false; // Si la valeur
            // n'est pas null mais qu'elle est aussi égale au nbAleatoire, on retourne faux
            //càd : si il y a le même numéro que nbAleatoire dans la colonne en question
        }

        // Boucler sur la ligne
        // Mêmes opérations que la colonne mais sur les lignes
        int ligne = i / 9;
        for (int x=0; x<9; x++){
            Integer valeur = getCase(grille, x, ligne);
            if (valeur != null && valeur.equals(nbAleatoire)) return false;
        }

        // Boucler dans les carrés
        // Cette partie nous permet de déterminer dans quel carré nous sommes
        // En utilisant la division ainsi que le calcul de la colonne/ligne précédemment, on peut
        // en multipliant par 3, déterminer où est ce que le carré en qst débute (soit colonne 0, 3 ou 6)
        int startX = (colone / 3) * 3; //coin supérieur gauche du carré
        int startY = (ligne / 3) * 3; //

        for (int x = startX; x < startX + 3; x++){ // On vérifie comme on l'a fait précédemment
            // lorsqu'on analysait la colonne et la ligne de manière individuelle
            // cela nous permet de bien respecter la règle du sudoku pour chaque carré, il doit y avoir
            // 1 seule fois le même numéro
            for (int y = startY; y < startY + 3; y++){
                Integer valeur = getCase(grille, x, y);
                if (valeur != null && valeur.equals(nbAleatoire)) return false;
            }
        }

        return true;
    }

    public void setCaseDepart(int x, int y, int valeur) { setCase(grilleDepart, x, y, valeur); }
    public void setCaseJoueur(int x, int y, int valeur) { setCase(grilleJoueur, x, y, valeur); }
    private void setCase(Integer[] grille, int x, int y, int valeur){
        if (x >= 0 && x < 9 && y >= 0 && y < 9){
            int index = y * 9 + x;
            grille[index] = valeur;
        } // pour mettre un numéro dans une case
    }

    public Integer getCaseDepart(int x, int y){ return getCase(grilleDepart, x, y); }
    public Integer getCaseJoueur(int x, int y){ return getCase(grilleJoueur, x, y); }
    // Récupération d'une case
    // Param : la grille en tant que Integer[], x et y int pour la position
    private Integer getCase(Integer[] grille, int x, int y){
        // Si x et y sont bien compris entre 0 et 9 : nécessaire car chaque carré
        // de la grille est 3x3, et que la liste de numéros de la grille est de taille 81
        // ça sert à ne pas sortir de la grille
        if (x >= 0 && x < 9 && y >= 0 && y < 9){
            // on forme un indice qui sera la position du numéro récupéré
            int index = y * 9 + x;
            // on regarde si il y a un numéro à cet indice
            if (grille[index] != null){
                return grille[index]; // on le renvoie
            }
        }// sinon on renvoie null
        return null;
    }
    public boolean estRemplie() {
        for (int i = 0; i < 81; i++) {
            if (this.grilleJoueur[i] == null) {
                return false; // Dès qu'on trouve une case vide, on s'arrête
            }
        }
        return true; // Si on a parcouru les 81 cases sans trouver de null, c'est plein
    }
// Accesseurs sur la grille
    public Integer[] getGrilleDepart(){ return grilleDepart; }
    public Integer[] getGrilleJoueur(){ return grilleJoueur; }
}
