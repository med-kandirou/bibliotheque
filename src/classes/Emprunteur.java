package classes;

public class Emprunteur {
    private static Emprunteur instance;  // The single instance of Emprunteur
    private int num;
    private String nom;
    private String prenom;
    private String email;
    private String tele;

    // Private constructor to prevent external instantiation
    private Emprunteur(Integer num, String nom, String prenom, String email, String tele) {
        this.num = num;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tele = tele;
    }

    // Public static method to get the instance of Emprunteur
    public static Emprunteur getInstance(Integer num, String nom, String prenom, String email, String tele) {
        if (instance == null) {
            instance = new Emprunteur(num, nom, prenom, email, tele);
        }
        return instance;
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
}
