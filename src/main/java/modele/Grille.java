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


        for (int x=0; x<9; x++){
            // -------------------- Debut backtracking --------------------

            boolean conditionsValides = false;
            while (!conditionsValides){

                List<Integer> chiffres = new ArrayList<>();
                for (int i=0; i<9; i++) { chiffres.add(i); }

                for (int y=0; y<9; y++){
                    int indexAleatoire = (int)(Math.random() * chiffres.size());
                    int chiffre = chiffres.remove(indexAleatoire);
                    setCase(grilleDepart, x, y, chiffre);
                }
            }
            // -------------------- Fin backtracking --------------------
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
