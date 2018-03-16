
DROP TABLE IF EXISTS Reservation;
DROP TABLE IF EXISTS Client;
DROP TABLE IF EXISTS Visite;

CREATE TABLE Visite (
	idVisite int NOT NULL AUTO_INCREMENT,
	typeVisite varchar(100) NOT NULL,
	ville varchar(40) NOT NULL,
	dateVisite date NOT NULL,
	prixVisite float(24) NOT NULL,
	PRIMARY KEY(idVisite)
);

CREATE TABLE Client (
	idClient int NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(idClient)
);

CREATE TABLE Reservation (
	idReservation int NOT NULL AUTO_INCREMENT,
	idVisite int,
	idClient int,
	nombreplaces int NOT NULL,
	booleanPaiementEffectue boolean NOT NULL,
	PRIMARY KEY(idReservation),
	FOREIGN KEY (idVisite) REFERENCES Visite(idVisite),
	FOREIGN KEY (idClient) REFERENCES Client(idClient)
);