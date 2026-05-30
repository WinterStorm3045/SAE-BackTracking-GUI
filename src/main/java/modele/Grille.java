package modele;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grille {
    private Integer[] grilleDepart;
    private Integer[] grilleJoueur;
    private float difficulte;

    public Grille(float difficulte){
        grilleDepart = new Integer[81];
        this.difficulte = difficulte;

        grilleDepart = genererGrille(0, new Integer[81]);

        System.out.println("Grille départ : " + Arrays.toString(grilleDepart));

        grilleJoueur = grilleDepart.clone();
        for (int i=0; i<81; i++){
            float test = (float)Math.random();
            if (test < difficulte){
                grilleJoueur[i] = null;
            }
        }
        System.out.println("Grille joueur : " + Arrays.toString(grilleDepart));
    }


    private Integer[] genererGrille(int index, Integer[] grille) {
        if (index == 81) { return grille; }

        List<Integer> nombres = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        java.util.Collections.shuffle(nombres);

        for (Integer nbAleatoire : nombres) {
            if (verifierConditions(index, nbAleatoire, grille)) {

                Integer[] nouvelleGrille = grille.clone();
                nouvelleGrille[index] = nbAleatoire;

                Integer[] resultat = genererGrille(index + 1, nouvelleGrille);

                if (resultat != null) { return resultat; }
            }
        }
        return null;
    }



    private boolean verifierConditions(int i, Integer nbAleatoire, Integer[] grille){
        // Boucler sur la colone
        int colone = i % 9;
        for (int y=0; y<9; y++){
            Integer valeur = getCase(grille, colone, y);
            if (valeur != null && valeur.equals(nbAleatoire)) return false;
        }

        // Boucler sur la ligne
        int ligne = i / 9;
        for (int x=0; x<9; x++){
            Integer valeur = getCase(grille, x, ligne);
            if (valeur != null && valeur.equals(nbAleatoire)) return false;
        }

        // Boucler dans les carrés
        int startX = (colone / 3) * 3; //coin supérieur gauche du carré
        int startY = (ligne / 3) * 3;

        for (int x = startX; x < startX + 3; x++){
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
        }
    }

    public Integer getCaseDepart(int x, int y){ return getCase(grilleDepart, x, y); }
    public Integer getCaseJoueur(int x, int y){ return getCase(grilleJoueur, x, y); }
    private Integer getCase(Integer[] grille, int x, int y){
        if (x >= 0 && x < 9 && y >= 0 && y < 9){
            int index = y * 9 + x;
            return grille[index];
        }
        return null;
    }

    public Integer[] getGrilleDepart(){ return grilleDepart; }
    public Integer[] getGrilleJoueur(){ return grilleJoueur; }
}