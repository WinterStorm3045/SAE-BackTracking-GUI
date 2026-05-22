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
                conditionsValides = true;
                System.out.println(nbAleatoire);

                // Boucler sur la colone
                int colone = i % 9;
                for (int y=0; y<9; y++){
                    Integer valeur = getCase(grilleDepart, colone, y);
                    conditionsValides = (valeur != nbAleatoire || valeur == null);
                    System.out.println(valeur != nbAleatoire || valeur == null);
                }

                // Boucler sur la ligne
                int ligne = i / 9;
                for (int x=0; x<9; x++){
                    Integer valeur = getCase(grilleDepart, x, ligne);
                    conditionsValides = (valeur != nbAleatoire || valeur == null);
                    System.out.println(valeur != nbAleatoire || valeur == null);
                }

                // Boucler dans les carrés
                for (int x=colone%3; x<9; x+=3){
                    for (int y=ligne%3; y<9; y+=3){
                        Integer valeur = getCase(grilleDepart, x, y);
                        conditionsValides = (valeur != nbAleatoire || valeur == null);
                        System.out.println(valeur != nbAleatoire || valeur == null);
                    }
                }
            }
            grilleDepart[i] = nbAleatoire;
        }

        System.out.println("Grille départ : " + Arrays.toString(grilleDepart));

        grilleJoueur = grilleDepart;
        for (int i=0; i<81; i++){
            float test = (float)Math.random();
            if (test < difficulte){
                grilleJoueur[i] = null;
            }
        }
        System.out.println("Grille joueur : " + Arrays.toString(grilleDepart));
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
        return Integer.MIN_VALUE;
    }

    public Integer[] getGrilleDepart(){ return grilleDepart; }
    public Integer[] getGrilleJoueur(){ return grilleJoueur; }
}
