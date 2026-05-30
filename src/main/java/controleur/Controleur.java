package controleur;

import javafx.event.Event;
import modele.Grille;
import vue.GridPaneJeu;
import vue.HBoxRoot;
import vue.VBoxControles;

public class Controleur {
    private static Grille modeleGrille;
    private static GridPaneJeu vueGrille;
    private static VBoxControles controles;

    private int posX;
    private int posY;

    public Controleur(){
        modeleGrille = HBoxRoot.getModeleGrille();
        vueGrille = HBoxRoot.getVueGrille();
        controles = HBoxRoot.getControles();
    }

    public void setPosition(Integer posX, Integer posY){
        this.posX = posX;
        this.posY = posY;
    }

    public void ajouterNumero(Integer numero){
        modeleGrille.setCaseJoueur(posX, posY, numero);
        vueGrille.updateValeurs(modeleGrille.getGrilleJoueur());
    }

    public void valider(){
        Integer[] grilleJoueur = modeleGrille.getGrilleJoueur();
        /*for (int i=0; i<81; i++){
            if (grilleJoueur[i] == null) return;
        }*/


        Integer[] grilleDepart = modeleGrille.getGrilleDepart();
        boolean[] grilleValide = new boolean[81];

        for (int i=0; i<81; i++){
            if (grilleJoueur[i] == grilleDepart[i]){
                grilleValide[i] = true;
            } else {
                grilleValide[i] = false;
            }
        }

        vueGrille.updateCouleurs(grilleValide);
    }

    public void solution(){
        Integer[] grille = modeleGrille.getGrilleDepart();
        vueGrille.updateValeurs(grille);
    }
}