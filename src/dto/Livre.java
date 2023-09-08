package dto;

public class Livre {
    private String isbn;
    private String titre;
    private String auteur;

    // Singleton instance

    public Livre(){}

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
