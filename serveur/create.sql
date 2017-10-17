DROP TABLE IF EXISTS participant;
CREATE TABLE participant(
  idParticipant INT PRIMARY KEY,
  nom CHAR(20),
  prenom CHAR(20)
);

DROP TABLE IF EXISTS evenement;
CREATE TABLE evenement(
  idEvenement INTEGER,
  nomEvenement VARCHAR(100) ,
  lieuEvenement VARCHAR(100),
  description VARCHAR(100),
  dateCreation TIMESTAMP,
  dateFinalisation TIMESTAMP,
  idParticipant FOREIGN KEY REFERENCES participant(idParticipant),
  PRIMARY KEY (idEvenement, idParticipant)
);

DROP TABLE IF EXISTS vote;
CREATE TABLE vote(
  idEvenement FOREIGN KEY REFERENCES evenement(idEvenement),
  idParticipant FOREIGN KEY REFERENCES participant(idParticipant),
  votes OTHER,
  PRIMARY KEY (idEvenement, idParticipant)
);


DROP TABLE IF EXISTS dateEvenement;
CREATE TABLE dateEvenement (

idDate PRIMARY KEY INTEGER,
dateHeure TIMESTAMP,
dureeMinutesEvenement INT );