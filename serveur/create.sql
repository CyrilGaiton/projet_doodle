DROP TABLE participant;
CREATE TABLE participant(
  id INT,
  nom CHAR(20),
  prenom CHAR(20)
);

DROP TABLE evenement;
CREATE TABLE evenement(
  id INT,

)

DROP TABLE vote;
CREATE TABLE vote(
  idEvenement INT,
  idParticipant BIGINT,
  votes OTHER,
  PRIMARY KEY (idEvenement, idParticipant),
  FOREIGN KEY (idParticipant) REFERENCES participant
);