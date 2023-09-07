package dto;

import java.util.Date;

public class Emprunt {
    private Date dateEmprunt;
    private Date dateRetour;

    private Exemplaire exemplaire;
    private Emprunteur emprunteur;

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }


    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public Emprunteur getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }

    private static Emprunt Instance;
    public Emprunt() {}

    public static Emprunt getInstance() {
        if (Instance == null) {
            Instance = new Emprunt();
        }
        return Instance;
    }
}
