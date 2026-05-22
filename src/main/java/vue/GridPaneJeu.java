package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.util.*;


public class GridPaneJeu extends GridPane {

    public GridPaneJeu(Integer[] valeurs){
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setHgap(1);
        this.setVgap(1);

        ToggleGroup cases = new ToggleGroup();

        for (int x=0; x<9; x++){
            for (int y=0; y<9; y++){

                // Pr rajouter une séparation entre les carrés
                Integer posX = x + x/3;
                Integer posY = y + y/3;

                // Valeurs manquantes
                if (valeurs[y*9+x] == null){
                    ToggleButton btn = new ToggleButton("");
                    btn.setToggleGroup(cases);
                    btn.setUserData(new Pair<>(x, y));
                    this.add(btn, posX, posY, 1, 1);
                    btn.setOnAction(event -> HBoxRoot.getControleur().setPosition(event));
                    btn.getStyleClass().add("grille-button");
                }
                // Valeurs déja présentes
                else {
                    Label label = new Label(valeurs[y*9+x].toString());
                    label.setUserData(new Pair<>(x, y));
                    this.add(label, posX, posY, 1, 1);
                    label.getStyleClass().add("grille-label");
                }
            }
        }
    }
}
