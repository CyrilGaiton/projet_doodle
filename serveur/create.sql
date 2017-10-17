DROP TABLE participant;
CREATE TABLE participant(
  id INT,
  nom CHAR(20),
  prenom CHAR(20)
);

DROP TABLE evenement;
CREATE TABLE evenement(
  id INT,
  nom CHAR(20),
  lieu CHAR(20),
  description CHAR(100),
  dateCreation TIMESTAMP,
  dateFinalisation TIMESTAMP,
  idCreateur INT
)

DROP TABLE vote;
CREATE TABLE vote(
  idEvenement INT,
  idParticipant INT,
  votes OTHER,
  PRIMARY KEY (idEvenement, idParticipant),
  FOREIGN KEY (idParticipant) REFERENCES participant,
  FOREIGN KEY (idEvenement) REFERENCES evenement
);

INSERT INTO participant VALUES (1, 'jean', 'dupont');
INSERT INTO evenement VALUES (1, 'eve1', 'toulon', 'blabla a toulon');