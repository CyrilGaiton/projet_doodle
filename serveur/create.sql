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
  nom VARCHAR(100) ,
  lieu VARCHAR(100),
  description VARCHAR(100),
  dateCreation OTHER,
  dateFinalisation OTHER,
  duree VARCHAR(20),
  PRIMARY KEY (idEvenement)
);

DROP TABLE IF EXISTS date;
CREATE TABLE date (
  idDate INT,
  date VARCHAR(20),
  PRIMARY KEY (idDate)
);

DROP TABLE IF EXISTS vote;
CREATE TABLE vote(
  idEvenement INT,
  idParticipant INT,
  idDate INT,
  PRIMARY KEY (idEvenement, idParticipant, idDate),
  FOREIGN KEY (idEvenement) REFERENCES evenement(idEvenement),
  FOREIGN KEY (idParticipant) REFERENCES utilisateur(idUtilisateur),
  FOREIGN KEY (idDate) REFERENCES date(idDate)

);


DROP TABLE IF EXISTS dateEvenement;
CREATE TABLE dateEvenement(
  idEvenement INT,
  idDate INT,
  PRIMARY KEY (idEvenement, idDate),
  FOREIGN KEY (idEvenement) REFERENCES evenement(idEvenement),
  FOREIGN KEY (idDate) REFERENCES date(idDate)
);

INSERT INTO evenement(idEvenement, nom, lieu, DESCRIPTION, duree) VALUES (0, 'eve1', 'toulon', 'blabla a toulon', '45min');
INSERT INTO utilisateur VALUES (0, 'jean', 'dupont');
INSERT INTO utilisateur VALUES (1, 'pierre', 'dupond');
INSERT INTO date(idDate, date) VALUES (0, '10/02/1999 8h30');
INSERT INTO date(idDate, date) VALUES (1, '11/03/2000 ');
INSERT INTO dateEvenement(idEvenement, idDate) VALUES (0, 0), (0,1);
INSERT INTO vote(idEvenement, idParticipant, idDate) VALUES (0, 0, 0), (0, 0, 1), (0, 1, 1);