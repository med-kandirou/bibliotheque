package helper;

import dao.EmpruntDao;
import dao.EmprunteurDao;
import dao.ExemplaireDao;
import dto.Emprunt;
import dto.Emprunteur;
import dao.LivreDao;
import dto.Exemplaire;
import dto.Livre;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Menu {
    // Private static instance variable
    private static Menu instance;

    // Private constructor to prevent external instantiation
    private Menu() {
    }

    // Public method to get the single instance of Menu
    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    // Display the menu options
    public void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Ajouter un livre :");
        System.out.println("2. Supprimer un livre :");
        System.out.println("3. Mettre ajour un livre :");
        System.out.println("4. Afficher les livres disponibles :");
        System.out.println("5. Ajouter un emprunteur :");
        System.out.println("6. Emprunter un livre :");
        System.out.println("7. Ajouter un Exempalire :");
        System.out.println("8. Retourner un livre :");
        System.out.println("9. Stattistique du bibliotheque :");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }


    Livre livre = Livre.getInstance();
    LivreDao livreDao =new LivreDao();
    Emprunteur emprunteur = Emprunteur.getInstance();
    EmprunteurDao emprunteurDao = new EmprunteurDao();

    Emprunt emprunt = Emprunt.getInstance();
    EmpruntDao empruntDao = new EmpruntDao();


    Exemplaire exemplaire = Exemplaire.getInstance();
    ExemplaireDao exemplaireDao = new ExemplaireDao();


    // Handle user choices
    public void router() {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Entrer ISBN: ");
                livre.setIsbn(scanner.next());
                System.out.print("Entrer le titre: ");
                scanner.nextLine();
                livre.setTitre(scanner.nextLine());
                System.out.print("Entrer Auteur: ");
                livre.setAuteur(scanner.nextLine());
                System.out.print(livreDao.ajouter(livre));
                this.displayMenu();
                this.router();
                break;
            case 2:
                System.out.println("Entrer le ISBN du livre :");
                livre.setIsbn(scanner.next());
                System.out.print(livreDao.supprimer(livre));
                this.displayMenu();
                this.router();
                break;
            case 3:
                System.out.println("Entrer le ISBN du livre a modifier :");
                String isbn=scanner.next();
                Livre liv= livreDao.recherche(isbn);
                System.out.print("Isbn : "+liv.getIsbn()+ "\nTitre :"+liv.getTitre()+ "\nAuteur :"+liv.getAuteur()+"\n");
                System.out.print("Modifier le titre (entrer=laisser la valeur initiale) : ");
                scanner.nextLine();
                String newTitre = scanner.nextLine();
                if (!newTitre.isEmpty()) {
                    liv.setTitre(newTitre);
                }
                System.out.print("Modifier l'auteur (entrer=laisser la valeur initiale) : ");
                String newAuteur = scanner.nextLine();
                if (!newAuteur.isEmpty()) {
                    liv.setAuteur(newAuteur);
                }
                liv= livreDao.maj(liv);
                System.out.print(liv.getTitre()+" a été bien modifié");
                break;
            case 4:
                System.out.println("La liste des livres diponibles :");
                List<Livre> livres= livreDao.afficher();
                for (Livre l : livres){
                    System.out.print("isbn : "+l.getIsbn() +" titre :" +l.getTitre()+" auteur :" +l.getAuteur()+"\n");
                }
                break;
            case 5:
                System.out.print("Entrer Le nom : ");
                emprunteur.setNom(scanner.next());
                System.out.print("Entrer le prenom: ");
                emprunteur.setPrenom(scanner.next());
                System.out.print("Entrer email : ");
                emprunteur.setEmail(scanner.next());
                System.out.print("Entrer telephone : ");
                emprunteur.setTele(scanner.next());
                emprunteur=emprunteurDao.ajouter(emprunteur);
                if(emprunteur!=null){
                    System.out.print("Emprunteur bien ajouté");
                }
                else{
                    System.out.print("une erreur est produit");
                }
                break;
            case 6:
                System.out.println("La liste des livres diponibles :");
                List<Livre> ls= livreDao.afficher();
                for (Livre l : ls){
                    System.out.print("isbn : "+l.getIsbn() +" titre : " +l.getTitre()+" auteur :" +l.getAuteur()+"\n");
                }
                System.out.print("Entrer le Isbn : ");
                livre.setIsbn(scanner.next());
                exemplaire.setLivre(livre);
                emprunt.setExemplaire(exemplaire);
                System.out.println("La liste des emprunteurs :");
                List<Emprunteur> emprnts= emprunteurDao.getemprunteur();
                for (Emprunteur emp : emprnts){
                    System.out.print("Num : "+emp.getNum() +" Nom : " +emp.getNom()+" Prenom : " +emp.getPrenom()+" tele :" +emp.getTele()+" Email :" +emp.getEmail()+"\n");
                }
                System.out.print("Entrer le Num du emprunteur : ");
                emprunteur.setNum(scanner.nextInt());
                emprunt.setEmprunteur(emprunteur);
                System.out.print("Entrer la date de retour (yyyy-MM-dd): ");
                emprunt.setDateRetour(Date.valueOf(scanner.next()));
                if(empruntDao.reserver(emprunt)!=null){
                    System.out.print("emprunté avec succes");
                }
                break;
            case 7:
                System.out.println("La liste des livres diponibles :");
                List<Livre> livs= livreDao.afficher();
                for (Livre l : livs){
                    System.out.print("isbn : "+l.getIsbn() +" titre : " +l.getTitre()+" auteur :" +l.getAuteur()+"\n");
                }
                System.out.print("Entrer le Isbn : ");
                livre.setIsbn(scanner.next());
                exemplaire.setLivre(livre);
                if(exemplaireDao.ajouter(exemplaire)!=null){
                    System.out.print("Exemplaire bien ajouté");
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
