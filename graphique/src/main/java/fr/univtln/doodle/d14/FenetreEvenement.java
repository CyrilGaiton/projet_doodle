package fr.univtln.doodle.d14;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FenetreEvenement {
    private int size;
    private Button btn_retour = new Button("Retour");
    private TableView <Participant> tableau = new TableView<>();
    private ObservableList<Participant> users = FXCollections.observableArrayList();
    private Button btn_add_participant = new Button("Ajouter un participant");

    // /!\      Test fonction Creer_Tableau()     /!\
    private List<Participant> testuti = new ArrayList<>();
    private List<String> testdat = new ArrayList<>();
    private List<String> testheu = new ArrayList<>();

    public FenetreEvenement(Group grp, Accueil accueil) {
        accueil.setEven(this);

        btn_retour.setOnAction(event -> {
            for (Participant usr:users) {
                System.out.println(usr);
            }
            Evenement_cache(grp);
            accueil.Accueil_affiche(grp);
        });

        btn_add_participant.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("nom");
            dialog.setTitle("Ajout d'un participant");
            dialog.setHeaderText(null);
            dialog.setContentText("Nom:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(this::Ajout_Participant);
        });

        // /!\      Test fonction Creer_Tableau()     /!\
        testuti.add(new Participant("gerrard", 2)); testuti.add(new Participant("marc", 2));
        testdat.add("10/10/2010"); testdat.add("02/02/2002");
        testheu.add("05h30"); testheu.add(null);
        Creer_Tableau(testuti, testdat, testheu);

        btn_add_participant.setLayoutX(450); btn_add_participant.setLayoutY(500);
        btn_retour.setLayoutY(31); btn_retour.setLayoutX(939);

        tableau.setItems(users);
        tableau.setEditable(true);
//        tableau.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableau.setLayoutX(100); tableau.setLayoutY(100);
    }

    public void Evenement_affiche(Group grp){
        grp.getChildren().addAll(btn_retour, btn_add_participant, tableau);
    }

    public void Evenement_cache(Group grp){
        grp.getChildren().removeAll(btn_retour, btn_add_participant, tableau);
    }

    public void Creer_Tableau(List<Participant> utilisateurs, List<String> calendar_str, List<String> heures_str){
        TableColumn <Participant, String> colonne1 = new TableColumn<>("Utilisateurs");
        colonne1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tableau.getColumns().add(colonne1);
//        colonne1.setStyle("-fx-background-color: blue");

        size = calendar_str.size();
        for (int i = 0; i < size; i++) {
            TableColumn <Participant, Boolean>colonne = new TableColumn<>(calendar_str.get(i)+"\n"+heures_str.get(i));
            int finalI = i;
            colonne.setCellFactory(CheckBoxTableCell.forTableColumn(param -> utilisateurs.get(param).voteProperty(finalI)));
            tableau.getColumns().add(colonne);
        }
        users.addAll(utilisateurs);
    }

    public void Ajout_Participant(String nom){
        tableau.getColumns().clear();
        users.clear();
        testuti.add(new Participant(nom, size));
        Creer_Tableau(testuti, testdat, testheu);
    }
}
