module com.example.TP_javaFX {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    exports modele;
    opens modele to javafx.fxml;
    exports controleur;
    opens controleur to javafx.fxml;
    exports vue;
    opens vue to javafx.fxml;
}