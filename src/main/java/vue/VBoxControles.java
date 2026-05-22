package vue;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class VBoxControles extends VBox {
    private GridPane gridPaneBoutons;


    public VBoxControles(){
        gridPaneBoutons = new GridPane();
        this.getChildren().add(gridPaneBoutons);

        List<Integer> chiffres = new ArrayList<>();
        for (int i=0; i<9; i++) { chiffres.add(i); }

        for (int x=0; x<3; x++){
            for (int y=0; y<3; y++){

                Integer numero = chiffres.get(y*3+x);

                Button btn = new Button(numero.toString());
                btn.setUserData(numero);
                gridPaneBoutons.add(btn, x, y, 1, 1);
                btn.setOnAction(event -> HBoxRoot.getControleur().ajouterNumero(event));
            }
        }
        Button btnValider = new Button("Valider");
        gridPaneBoutons.add(btnValider, 0, 3, 3, 1);
        btnValider.setOnAction(event -> HBoxRoot.getControleur().valider());
    }
}
