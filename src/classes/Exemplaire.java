package classes;

public class Exemplaire {
    private int id;
    private Statut statut;
    private String isbn;

    public enum Statut { Disponible, Emprunte, Perdu }

    // Private static instance variable to hold the single instance of Exemplaire
    private static Exemplaire Instance;

    // Private constructor to prevent external instantiation
    private Exemplaire(int id, Statut statut) {
        this.id = id;
        this.statut = statut;
    }

    // Public method to provide access to the single instance (Singleton)
    public static Exemplaire getInstance(int id, Statut statut) {
        if (Instance == null) {
            Instance = new Exemplaire(id, statut);
        }
        return Instance;
    }

    // Other methods and attributes as needed

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
