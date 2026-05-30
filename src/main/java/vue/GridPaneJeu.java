package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.util.*;


public class GridPaneJeu extends GridPane {
    private ToggleButton[] mesBoutons = new ToggleButton[81];
    public GridPaneJeu(Integer[] valeurs){
        // espacement entre les différents composants graphiques
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setHgap(4);
        this.setVgap(4);

        ToggleGroup cases = new ToggleGroup();
        // groupe de boutons pour les cases

        for (int y=0; y<9; y++){
            for (int x=0; x<9; x++){

                int posX = x; //obligé sinon jpp appeler le controloeur avec x et y
                int posY = y;
                int index = y * 9 + x;

                // Pr rajouter une séparation entre les carrés de 3x3
                int posGrilleX = x + x/3;
                int posGrilleY = y + y/3;

                // Valeurs manquantes
                if (valeurs[index] == null){ // Si, dans la grille de numéros, la case courante est vide
                    ToggleButton btn = new ToggleButton(""); // on crée un bouton sans intitulé
                    btn.setToggleGroup(cases); // on l'ajoute au groupe de boutons
                    mesBoutons[index] = btn;
                    this.add(btn, posGrilleX, posGrilleY, 1, 1); // on l'ajoute au gridpane
                    // On enregistre les coordonnées de la case sur laquelle l'utilisateur a cliqué
                    btn.setOnAction(event -> HBoxRoot.getControleur().setPosition(posX, posY));
                    btn.getStyleClass().add("grille-button"); // On transforme la case en question comme
                    // une case remplie
                }

                // Valeurs déja présentes
                // On applique la même logique pour les valeurs absentes
                // mais cette fois ci sans boutons pour pas que les cases par défaut soient modifiables
                else {
                    Label label = new Label(valeurs[y*9+x].toString());
                    this.add(label, posGrilleX, posGrilleY, 1, 1);
                    label.getStyleClass().add("grille-label");
                }
            }
        }
    }

    public void update(Integer[] grille) {
        // On récupère le modèle de la grille pour pouvoir tester les conditions de doublon
        modele.Grille modeleGrille = HBoxRoot.getModeleGrille();

        for (int i = 0; i < 81; i++) {
            if (mesBoutons[i] != null) {

                // mise à jour du texte
                if (grille[i] != null) {
                    mesBoutons[i].setText(grille[i].toString());


                    Integer nbAVerifier = grille[i];
                    grille[i] = null;

                    if (!modeleGrille.verifierConditions(i, nbAVerifier, grille)) {
                        // Si il y a un doublon on applique le style d'erreur s'il n'y est pas déjà
                        if (!mesBoutons[i].getStyleClass().contains("grille-erreur")) {
                            mesBoutons[i].getStyleClass().add("grille-erreur");
                        }
                    } else {
                        // Si tout est correct on retire le style d'erreur
                        mesBoutons[i].getStyleClass().remove("grille-erreur");
                    }

                    grille[i] = nbAVerifier; // On remet la valeur en place après le test

                } else {
                    // Si la case est vidée on efface le texte et on enlève l'erreur
                    mesBoutons[i].setText("");
                    mesBoutons[i].getStyleClass().remove("grille-erreur");
                }
            }
        }
    }
    public void valider(Integer[] grilleSolution) {
        // On récupère la grille actuelle du joueur pour comparer
        Integer[] grilleJoueur = HBoxRoot.getModeleGrille().getGrilleJoueur();

        for (int i = 0; i < 81; i++) {
            // on  vailde que les cases où le joueur a pu taper quelque chose (les boutons)
            if (mesBoutons[i] != null) {

                // on nettoie les anciens styles de validation au cas où il reclique plusieurs fois
                mesBoutons[i].getStyleClass().removeAll("grille-valide", "grille-invalide", "grille-erreur");

                // si la case est vide, on peut soit la laisser normale, soit la mettre en rouge

                }
                // Si le chiffre du joueur correspond au chiffre de la solution
                if (grilleJoueur[i].equals(grilleSolution[i])) {
                    mesBoutons[i].getStyleClass().add("grille-valide");
                }
                // Si le chiffre est faux
                else {
                    mesBoutons[i].getStyleClass().add("grille-invalide");
                }
            }
        }
    }


