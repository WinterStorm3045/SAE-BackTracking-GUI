package controleur;

import javafx.event.Event;
import vue.GridPaneJeu;
import vue.HBoxRoot;
import vue.VBoxControles;

public class Controleur {
    private static GridPaneJeu vueGrille;
    private static VBoxControles controles;

    public void setPosition(Event event){
        vueGrille = HBoxRoot.getVueGrille();
        controles = HBoxRoot.getControles();

    }

    public void ajouterNumero(Event event){

    }

    public void valider(){

    }
}
