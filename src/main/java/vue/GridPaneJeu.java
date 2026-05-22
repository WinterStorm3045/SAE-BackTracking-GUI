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

                int posX = x; //obligé sinon jpp appeler le controloeur avec x et y
                int posY = y;

                // Pr rajouter une séparation entre les carrés
                int posGrilleX = x + x/3;
                int posGrilleY = y + y/3;

                // Valeurs manquantes
                if (valeurs[y*9+x] == null){
                    ToggleButton btn = new ToggleButton("");
                    btn.setToggleGroup(cases);
                    this.add(btn, posGrilleX, posGrilleY, 1, 1);
                    btn.setOnAction(event -> HBoxRoot.getControleur().setPosition(posX, posY));
                    btn.getStyleClass().add("grille-button");
                }

                // Valeurs déja présentes
                else {
                    Label label = new Label(valeurs[y*9+x].toString());
                    this.add(label, posGrilleX, posGrilleY, 1, 1);
                    label.getStyleClass().add("grille-label");
                }
            }
        }
    }

    public void update(Integer[] grille){
        for (int i=0; i<81; i++){
            if (grille[i] != null && this.getChildren().get(i) instanceof ToggleButton) {
                ((ToggleButton)this.getChildren().get(i)).setText(grille[i].toString());
            }
        }
    }
}
