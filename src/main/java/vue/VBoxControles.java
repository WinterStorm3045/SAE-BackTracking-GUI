package vue;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class VBoxControles extends VBox {
    private GridPane gridPaneBoutons;
    private Button btnValider;
    private Button btnSolution;

    public VBoxControles(){
        gridPaneBoutons = new GridPane();
        this.btnValider = new Button("Vaider");
        this.btnSolution = new Button("Solution");

        this.getChildren().add(gridPaneBoutons);

        List<Integer> chiffres = new ArrayList<>();
        for (int i=1; i<10; i++) { chiffres.add(i); }

        for (int x=0; x<3; x++){
            for (int y=0; y<3; y++){

                Integer numero = chiffres.get(y*3+x);

                Button btn = new Button(numero.toString());
                btn.setUserData(numero);
                gridPaneBoutons.add(btn, x, y, 1, 1);
                btn.setOnAction(event -> HBoxRoot.getControleur().ajouterNumero(numero));
            }
        }
        gridPaneBoutons.add(btnValider, 0, 3, 3, 1);
        gridPaneBoutons.add(btnSolution,4,3,3,1);
        btnValider.setOnAction(event -> {
            HBoxRoot.getControleur().lancerValidation();
        });
        btnSolution.setOnAction(event -> {
            HBoxRoot.getControleur().afficherSolution();
        });
        ;

    }
    public Button getBtnValider() {
        return this.btnValider; // Permet de récupérer le bouton pour changer son texte
    }
}
