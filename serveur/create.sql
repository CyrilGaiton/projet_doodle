DROP TABLE IF EXISTS utilisateur;
CREATE TABLE utilisateur(
  idUtilisateur INT,
  nom CHAR(20),
  prenom CHAR(20),
  PRIMARY KEY (idUtilisateur)
);

DROP TABLE IF EXISTS evenement;
CREATE TABLE evenement(
  idEvenement INT,
  nomEvenement VARCHAR(100) ,
  lieuEvenement VARCHAR(100),
  description VARCHAR(100),
  dateCreation TIMESTAMP,
  dateFinalisation TIMESTAMP,
  idCreateur INT,
  PRIMARY KEY (idEvenement, idCreateur),
  FOREIGN KEY (idCreateur) REFERENCES utilisateur(idUtilisateur)
);


DROP TABLE IF EXISTS vote;
CREATE TABLE vote(
  idEvenement INT,
  idParticipant INT,
  votes OTHER,
  PRIMARY KEY (idEvenement, idParticipant),
  FOREIGN KEY (idEvenement) REFERENCES evenement(idEvenement),
  FOREIGN KEY (idParticipant) REFERENCES utilisateur(idUtilisateur)
);


DROP TABLE IF EXISTS dateEvenement;
CREATE TABLE dateEvenement (
  idDate INT,
  dateHeure TIMESTAMP,
  dureeMinutesEvenement INT,
  PRIMARY KEY (idDate)
);

INSERT INTO utilisateur VALUES (1, 'jean', 'dupont');
INSERT INTO evenement(idEvenement, nomEvenement, lieuEvenement, DESCRIPTION, idCreateur) VALUES (1, 'eve1', 'toulon', 'blabla a toulon', 1);
INSERT INTO vote (idEvenement, idParticipant, votes) VALUES (1, 1, null);