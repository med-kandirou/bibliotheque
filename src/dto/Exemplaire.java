package dto;

public class Exemplaire {
    private int id;
    private Statut statut;
    private Livre livre;

    public enum Statut { Disponible, Emprunte, Perdu }

    // Private static instance variable to hold the single instance of Exemplaire
    private static Exemplaire Instance;

    // Private constructor to prevent external instantiation
    public Exemplaire() {

    }

    // Public method to provide access to the single instance (Singleton)
    public static Exemplaire getInstance() {
        if (Instance == null) {
            Instance = new Exemplaire();
        }
        return Instance;
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
