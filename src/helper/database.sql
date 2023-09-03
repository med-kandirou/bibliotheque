CREATE DATABASE biblio;
USE biblio;

CREATE TABLE livre (
    isbn VARCHAR(13) PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    auteur VARCHAR(255),
    quantite int
);

CREATE TABLE exemplaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(13),
    statut ENUM('disponible', 'emprunté', 'perdu'),
    FOREIGN KEY (isbn) REFERENCES livre(isbn)
);

CREATE TABLE emprunteur (
    Num INT AUTO_INCREMENT PRIMARY KEY,
    Nom VARCHAR(255),
    Prenom VARCHAR(255),
    Email VARCHAR(255),
    Tele VARCHAR(20)
);

CREATE TABLE emprunt (
    id INT AUTO_INCREMENT PRIMARY KEY,
    emprunteur_id INT,
    exemplaire_id INT,
    date_emprunt DATE,
    date_retour DATE,
    FOREIGN KEY (emprunteur_id) REFERENCES emprunteur(Num),
    FOREIGN KEY (exemplaire_id) REFERENCES exemplaire(id)
);

