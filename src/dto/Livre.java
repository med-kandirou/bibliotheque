package dto;

public class Livre {
    private String isbn;
    private String titre;
    private String auteur;

    // Singleton instance
    private static Livre instance;

    public Livre(){}

    // Private constructor to prevent external instantiation
    private Livre(String isbn, String titre, String auteur) {
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
    }

    // Public method to get the Singleton instance
    public static Livre getInstance() {
        if (instance == null) {
            instance = new Livre();
        }
        return instance;
    }

    // Getters and setters for other attributes
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getAuteur() {
        return auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

}
