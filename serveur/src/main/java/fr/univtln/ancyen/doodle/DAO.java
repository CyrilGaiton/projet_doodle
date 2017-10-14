package fr.univtln.ancyen.doodle;

import java.sql.Connection;
import fr.univtln.ancyen.doodle.ConnectionDatabase;


public abstract class DAO<T> {

    public Connection connect = ConnectionDatabase.getInstance();



    //Permet de creer un objet dans la base de donnees.


    public abstract T create (T obj);


    // Permet de modifier un objet existant dans la base de donnees.

    public abstract T  update (T obj);

    // Permet de supprimer un objet dans la base de donnees.

    public abstract void delete(T obj);






}
