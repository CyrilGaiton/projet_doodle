package fr.univtln.doodle.d14;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class FenetreEvenement {
    private Button btn_retour = new Button("Retour");
    private TableView <Participant> tableau = new TableView<>();
    private ObservableList<Participant> users = FXCollections.observableArrayList();

    // /!\      Test fonction Creer_Tableau()     /!\
    private List<Participant> testuti = new ArrayList<>();
    private List<String> testdat = new ArrayList<>();
    private List<String> testheu = new ArrayList<>();

    public FenetreEvenement(Group grp, Accueil accueil) {
        accueil.setEven(this);

        tableau.setEditable(true);
        btn_retour.setLayoutY(31); btn_retour.setLayoutX(939);
        btn_retour.setOnAction(event -> {
            for (Participant usr:users) {
                System.out.println(usr);
            }
            Evenement_cache(grp);
            accueil.Accueil_affiche(grp);
        });

        // /!\      Test fonction Creer_Tableau()     /!\
        testuti.add(new Participant("gerrard", 2)); testuti.add(new Participant("marc", 2));
        testdat.add("10/10/2010"); testdat.add("02/02/2002");
        testheu.add("05h30"); testheu.add(null);
        Creer_Tableau(testuti, testdat, testheu);

        tableau.setItems(users);
//        tableau.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableau.setLayoutX(100); tableau.setLayoutY(100);
    }

    public void Evenement_affiche(Group grp){
        grp.getChildren().addAll(btn_retour, tableau);
    }

    public void Evenement_cache(Group grp){
        grp.getChildren().removeAll(btn_retour, tableau);
    }

    public void Creer_Tableau(List<Participant> utilisateurs, List<String> calendar_str, List<String> heures_str){
        TableColumn <Participant, String> colonne1 = new TableColumn<>("Utilisateurs");
        colonne1.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tableau.getColumns().add(colonne1);

        int size = calendar_str.size(), i = 0;
        while (i < size){
            TableColumn <Participant, Boolean>colonne = new TableColumn<>(calendar_str.get(i)+"\n"+heures_str.get(i));
            int finalI = i;
            colonne.setCellFactory(CheckBoxTableCell.forTableColumn(param -> utilisateurs.get(param).voteProperty(finalI)));
            tableau.getColumns().add(colonne);
            i++;
        }
        users.addAll(utilisateurs);
    }
}
