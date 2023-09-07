CREATE DATABASE biblio;
\c biblio;

CREATE TABLE livre (
                       isbn VARCHAR(13) PRIMARY KEY,
                       titre VARCHAR(255) NOT NULL,
                       auteur VARCHAR(255),
                       quantite INT
);

CREATE TABLE exemplaire (
                            id SERIAL PRIMARY KEY,
                            isbn VARCHAR(13) REFERENCES livre(isbn) ON UPDATE CASCADE ON DELETE CASCADE,
                            statut VARCHAR(20) CHECK (statut IN ('disponible', 'emprunté', 'perdu'))
);

CREATE TABLE emprunteur (
                            num SERIAL PRIMARY KEY,
                            nom VARCHAR(255),
                            prenom VARCHAR(255),
                            email VARCHAR(255),
                            tele VARCHAR(20)
);

CREATE TABLE emprunt (
                         id SERIAL PRIMARY KEY,
                         emprunteur_id INT REFERENCES emprunteur(num) ON UPDATE CASCADE ON DELETE CASCADE,
                         exemplaire_id INT REFERENCES exemplaire(id) ON UPDATE CASCADE ON DELETE CASCADE,
                         date_emprunt DATE,
                         date_retour DATE
);

ALTER TABLE your_table_name
ALTER COLUMN your_column_name SET DEFAULT your_default_value;