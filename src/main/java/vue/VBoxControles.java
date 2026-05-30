package vue;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class VBoxControles extends VBox {
    private GridPane gridPaneBoutons;
    private HBox hBoxValidation;


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
        
        hBoxValidation = new HBox();
        this.getChildren().add(hBoxValidation);
        
        Button btnAide = new Button("Aide");
        hBoxValidation.getChildren().add(btnAide);
        btnAide.getStyleClass().add("controle-button");
        btnAide.getStyleClass().add("valider-button");
        btnAide.setOnAction(event -> HBoxRoot.getControleur().solution());

        Button btnValider = new Button("Valider");
        hBoxValidation.getChildren().add(btnValider);
        btnValider.getStyleClass().add("controle-button");
        btnValider.getStyleClass().add("valider-button");
        btnValider.setOnAction(event -> HBoxRoot.getControleur().valider());
    }
}