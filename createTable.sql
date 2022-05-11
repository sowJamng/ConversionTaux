use mysqdb;
CREATE TABLE Taux
(
    idTaux int(10)  NOT NULL,
    monnaieA VARCHAR(10) NULL,
    monnaieB VARCHAR(32)  NULL,
    taux DOUBLE(19,4) NULL,
    CONSTRAINT PK_idTaux PRIMARY KEY (idTaux)
   ) ;
CREATE TABLE favori
(
    idFavori int(10)  NOT NULL,
    libelleFavori varchar NULL,
    leTaux_idTaux int(10)  NOT NULL,
    CONSTRAINT PK_idFavori PRIMARY KEY (idFavori),
    CONSTRAINT FK_idtaux FOREIGN KEY (leTaux_idTaux) references Taux(idTaux)
) ;

insert into Taux values (1, 'euros', 'dollars', 1.27);
insert into Taux values (2, 'dollars', 'euros', 0.78);
insert into Taux values (3, 'euros', 'yen', 149.53);
insert into Taux values (4, 'yen', 'euros', 0.0066);

CREATE TABLE abonne
(
    idAbonne int(10)  NOT NULL,
    login VARCHAR(10) NULL,
    passwd VARCHAR(32)  NULL,
    CONSTRAINT PK_idAbonne PRIMARY KEY (idAbonne)
) ;
insert into Abonne values (1, 'maodo', 'maodo');

CREATE TABLE abonne_Favori
(
    AbonneEntity_idAbonne  int(6) NOT NULL,
    lesFavoris_idFavori int(10) NOT NULL,
    CONSTRAINT PK_Abonne_Favori PRIMARY KEY (AbonneEntity_idAbonne, lesFavoris_idFavori),
    CONSTRAINT FK_AF FOREIGN KEY (AbonneEntity_idAbonne)
        REFERENCES Abonne (idAbonne),
    CONSTRAINT FK_FA FOREIGN KEY (lesFavoris_idFavori)
        REFERENCES Favori (idFavori)
);
CREATE TABLE admin
(
    idAdmin int(10)  NOT NULL,
    login VARCHAR(10) NULL,
    passwd VARCHAR(32)  NULL,
    CONSTRAINT PK_idAdmin PRIMARY KEY (idAdmin)
) ;
insert into admin values (1, 'monk', 'sow');


