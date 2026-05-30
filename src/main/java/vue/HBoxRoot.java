package vue;
import javafx.geometry.Insets;
import modele.Grille;
import controleur.Controleur;
import javafx.scene.layout.HBox;

public class HBoxRoot extends HBox {
    private static Grille modeleGrille; //
    private static GridPaneJeu vueGrille;
    private static VBoxControles controles;
    private static Controleur controleur;

    public HBoxRoot() {
        modeleGrille = new Grille(0.6f); // on met la difficulté de la grille
        vueGrille = new GridPaneJeu(modeleGrille.getGrilleJoueur());
        controles = new VBoxControles();
        controleur = new Controleur();

        this.setPadding(new Insets(15));
        this.setSpacing(20);
        this.getChildren().addAll(vueGrille, controles);
    }

    // Accesseurs
    public static Grille getModeleGrille() { return modeleGrille; }
    public static GridPaneJeu getVueGrille() { return vueGrille; }
    public static VBoxControles getControles() { return controles; }
    public static Controleur getControleur() { return controleur; }
}
