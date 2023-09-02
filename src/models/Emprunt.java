package models;

import java.util.Date;

public class Emprunt {
    private Date dateEmprunt;
    private Date dateRetour;

    private Livre livre;
    private Emprunteur emprunteur;

    public Emprunt(Date dateEmprunt, Date dateRetour) {
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }
}
