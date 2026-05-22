package vue;

import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.util.*;


public class GridPaneJeu extends GridPane {

    public GridPaneJeu(Integer[] valeurs){
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setHgap(10);
        this.setVgap(10);

        ToggleGroup cases = new ToggleGroup();
        for (int x=0; x<9; x++){
            for (int y=0; y<9; y++){
                String texte = (valeurs[y*9+x] == null) ? "" : valeurs[y*9+x].toString();

                ToggleButton btn = new ToggleButton(texte);
                btn.setToggleGroup(cases);
                btn.setUserData(new Pair<>(x, y));
                this.add(btn, x, y, 1, 1);
                btn.setOnAction(event -> HBoxRoot.getControleur().setPosition(event));
            }
        }
    }
}
