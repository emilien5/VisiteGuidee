
DROP TABLE IF EXISTS Visite;

CREATE TABLE Utilisateur (
	idUtilisateur int NOT NULL AUTO_INCREMENT,
	nom varchar(40) NOT NULL,
	prenom varchar(40) NOT NULL,
	motDePasse varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	adresse varchar(100) NOT NULL,
	tel varchar(20) NOT NULL,
	PRIMARY KEY(idUtilisateur)
);