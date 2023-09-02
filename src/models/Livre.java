package models;

public class Livre {
    private String isbn;
    private String titre;
    private String auteur;
    private int quantite;

    // Singleton instance
    private static Livre instance;

    // Private constructor to prevent external instantiation
    private Livre(String isbn, String titre, String auteur, int quantite) {
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.quantite = quantite;
    }

    // Public method to get the Singleton instance
    public static Livre getInstance(String isbn, String titre, String auteur, int quantite) {
        if (instance == null) {
            instance = new Livre(isbn, titre, auteur, quantite);
        }
        return instance;
    }

    // Getters and setters for other attributes

    // You can add getters and setters for the attributes as needed
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
