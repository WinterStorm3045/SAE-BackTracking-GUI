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
        vueGrille.update(modeleGrille.getGrilleJoueur());
    }

    public void lancerValidation() {
        // on récupère la grille passée dans le contrôleur
        modele.Grille modeleGrille = HBoxRoot.getModeleGrille();

        // on vérifie si la grille est completement remplie
        if (!modeleGrille.estRemplie()) {
            // si non, on change le bouton valider et on affiche grille incomplète
            HBoxRoot.getControles().getBtnValider().setText("Grille incomplète !");
            return;
        }

        // on remet le bouton valider normalement si la grille est pleine
        HBoxRoot.getControles().getBtnValider().setText("Valider");

        Integer[] solution = modeleGrille.getGrilleDepart(); // on recupere la grillr de depart
        HBoxRoot.getVueGrille().valider(solution); // on lance la validation avec .valider
    }
    public void afficherSolution() {
        modele.Grille modeleGrille = HBoxRoot.getModeleGrille(); // on recupere la grille
        Integer[] solution = modeleGrille.getGrilleDepart(); // on met dans un tableau solution la grille de depart
        Integer[] grilleJoueur = modeleGrille.getGrilleJoueur(); // on recupere la grille du joueur

        // on remplit la solution dans la grille du joueur
        for (int i = 0; i < 81; i++) {
            grilleJoueur[i] = solution[i];
        }

        // on demande à la vue de se mettre à jour graphiquement
        HBoxRoot.getVueGrille().update(grilleJoueur);
    }
}
