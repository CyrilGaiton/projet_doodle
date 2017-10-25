package fr.univtln.ancyen.doodle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Participant {
    private StringProperty nom;
    private List<BooleanProperty> votes = new ArrayList<>();

    public Participant(String nom, int nb_votes) {
        this.nom = new SimpleStringProperty(nom);
        for (int i = 0; i < nb_votes; i++) {
            votes.add(new SimpleBooleanProperty(false));
        }
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public List<BooleanProperty> getVotes() {
        return votes;
    }

    public void setVotes(List<BooleanProperty> votes) {
        this.votes = votes;
    }

    public BooleanProperty voteProperty(int i){
        return votes.get(i);
    }

    public void setVote(boolean vote, int i){
        voteProperty(i).set(vote);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "nom=" + nom +
                ", votes=" + votes +
                '}';
    }
}
