package fr.univtln.doodle.d14;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.util.Optional;

public class  Fenetre {
    private String langue = "Français";
    private Group root = new Group();
    private Scene scene = new Scene(root, 1000, 562, Color.LIGHTBLUE);
    private Menu fichier_menu = new Menu("Fichier");
    private MenuItem exporter_item = new MenuItem("Exporter");
    private MenuItem imprimer_item = new MenuItem("Imprimer");

    private Menu parametre_menu = new Menu("Parametres");
    private MenuItem langue_item = new MenuItem("Changement langue");
    private MenuItem pseudo_item = new MenuItem("Changement pseudo");
    private MenuBar mb = new MenuBar();

    public Fenetre() {
        exporter_item.setOnAction(event -> System.out.println("Exportation ..."));

        imprimer_item.setOnAction(event -> System.out.println("Impression ..."));

        langue_item.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Changement de langue.");
            alert.setHeaderText(null);
            alert.setContentText("Choisissez votre langue.");
            ButtonType button_fr = new ButtonType("Français");
            ButtonType button_eng = new ButtonType("English");
            ButtonType button_cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(button_fr, button_eng, button_cancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == button_fr){
                langue = "Français";
            } else if (result.get() == button_eng) {
                langue = "English";
            } else {
                System.out.println("closed");
            }
        });

        pseudo_item.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("pseudo");
            dialog.setTitle("Changement de pseudo");
            dialog.setHeaderText(null);
            dialog.setContentText("Nouveau pseudo:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> System.out.println("Nouveau pseudo: " + name));
        });
        fichier_menu.getItems().addAll(exporter_item, imprimer_item);
        parametre_menu.getItems().addAll(langue_item, pseudo_item);
        mb.getMenus().addAll(fichier_menu, parametre_menu);
        mb.setStyle("-fx-background-color: green; -fx-border-color: darkred ");
        mb.setMinWidth(1000);
        root.getChildren().add(mb);
    }

    public Group getRoot() {
        return root;
    }

    public Scene getScene() {
        return scene;
    }

    public MenuBar getMb() {
        return mb;
    }
}
