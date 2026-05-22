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

        for (int i=0; i<81; i++){
            boolean conditionsValides = false;
            Integer nbAleatoire = null;

            while (!conditionsValides){
                nbAleatoire = (int)(Math.random()*9);
                conditionsValides = verifierConditions(i, nbAleatoire);
            }
            grilleDepart[i] = nbAleatoire;
        }

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



    private boolean verifierConditions(int i, Integer nbAleatoire){
        // Boucler sur la colone
        int colone = i % 9;
        for (int y=0; y<9; y++){
            Integer valeur = getCase(grilleDepart, colone, y);
            if (valeur == nbAleatoire) return false;
        }

        // Boucler sur la ligne
        int ligne = i / 9;
        for (int x=0; x<9; x++){
            Integer valeur = getCase(grilleDepart, x, ligne);
            if (valeur == nbAleatoire) return false;
        }

        // Boucler dans les autres carrés
        for (int x=colone%3; x<9; x+=3){
            for (int y=ligne%3; y<9; y+=3){
                Integer valeur = getCase(grilleDepart, x, y);
                if (valeur == nbAleatoire) return false;
            }
        }

        // Boucler dans les carrés
        for (int x=colone-colone%3; x<3; x++){
            for (int y=ligne-ligne%3; y<3; y++){
                Integer valeur = getCase(grilleDepart, x, y);
                if (valeur == nbAleatoire) return false;
            }
        }
        System.out.println("valide");
        return true;
    }

    public void setCase(Integer[] grille, int x, int y, int chiffre){
        if (x >= 0 && x < 9 && y >= 0 && y < 9){
            int index = y * 9 + x;
            grille[index] = chiffre;
        }
    }

    public Integer getCase(Integer[] grille, int x, int y){
        if (x >= 0 && x < 9 && y >= 0 && y < 9){
            int index = y * 9 + x;
            if (grille[index] != null){
                return grille[index];
            }
        }
        return null;
    }

    public Integer[] getGrilleDepart(){ return grilleDepart; }
    public Integer[] getGrilleJoueur(){ return grilleJoueur; }
}
