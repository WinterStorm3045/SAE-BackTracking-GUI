package vue;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class VBoxControles extends VBox {
    private GridPane gridPaneBoutons;


    public VBoxControles(){
        gridPaneBoutons = new GridPane();
        this.getStyleClass().add("controles");
        this.getChildren().add(gridPaneBoutons);

        List<Integer> chiffres = new ArrayList<>();
        for (int i=9; i>0; i--) { chiffres.add(i); }

        for (int x=0; x<3; x++){
            for (int y=0; y<3; y++){

                Integer numero = chiffres.get(y*3+x);

                Button btn = new Button(numero.toString());
                btn.setUserData(numero);
                gridPaneBoutons.add(btn, x, y, 1, 1);
                btn.getStyleClass().add("controle-button");
                btn.setOnAction(event -> HBoxRoot.getControleur().ajouterNumero(numero));
            }
        }

        Button btn0 = new Button("0");
        btn0.setUserData(0);
        gridPaneBoutons.add(btn0, 0, 3, 1, 1);
        btn0.getStyleClass().add("controle-button");
        btn0.setOnAction(event -> HBoxRoot.getControleur().ajouterNumero(0));

        Button btnValider = new Button("Valider");
        gridPaneBoutons.add(btnValider, 1, 3, 2, 1);
        btnValider.getStyleClass().add("controle-button");
        btnValider.getStyleClass().add("valider-button");
        btnValider.setOnAction(event -> HBoxRoot.getControleur().valider());
    }
}