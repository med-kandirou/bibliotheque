package dto;

public class Exemplaire {
    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private Statut statut;
    private Livre livre;

    public enum Statut { Disponible, Emprunte, Perdu }

    // public constructor to prevent external instantiation
    public Exemplaire() {

    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    // Other methods and attributes as needed
    public int getId() {
        return id;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }


}
