package dto;

public class Emprunteur {
    private static Emprunteur instance;  // The single instance of Emprunteur


    private int num;
    private String nom;
    private String prenom;
    private String email;
    private String tele;

    // Public static method to get the instance of Emprunteur
    public static Emprunteur getInstance() {
        if (instance == null) {
            instance = new Emprunteur();
        }
        return instance;
    }


    public void setNum(int num) {
        this.num = num;
    }

    // Getter methods for the attributes
    public int getNum() {
        return num;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTele() {
        return tele;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }
}
