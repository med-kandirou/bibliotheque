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
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        System.out.println("\n\nMenu:");
        System.out.println("1. Ajouter un livre :");//complet
        System.out.println("2. Supprimer un livre :");//complet
        System.out.println("3. Mettre ajour un livre :");//complet
        System.out.println("4. Afficher les livres disponibles :");//complet
        System.out.println("5. Ajouter un emprunteur :");//complet
        System.out.println("6. Emprunter un livre :");//complet
        System.out.println("7. Ajouter un Exempalire :");//complet
        System.out.println("8. Retourner un livre :");//complet
        System.out.println("9. Rechercher un livre :");//complet
        System.out.println("10. Statistique du bibliotheque :");//complet
        System.out.println("11. Exit");
        System.out.print("Enter your choice: ");
    }


    Livre livre = new Livre();
    LivreDao livreDao =LivreDao.getInstance();
    Emprunteur emprunteur = new Emprunteur();
    EmprunteurDao emprunteurDao =EmprunteurDao.getInstance();
    Emprunt emprunt =new Emprunt();
    EmpruntDao empruntDao =EmpruntDao.getInstance();
    Exemplaire exemplaire =new Exemplaire();
    ExemplaireDao exemplaireDao =ExemplaireDao.getInstance();

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
                livre=livreDao.ajouter(livre);
                if(livre!=null){
                    System.out.print(livre.getTitre()+" a été bien ajouté");
                }
                break;
            case 2:
                System.out.println("Entrer le ISBN du livre :");
                scanner.nextLine();
                livre.setIsbn(scanner.nextLine());
                livre=livreDao.supprimer(livre);
                if(livre!=null){
                        System.out.print("Le livre a été bien supprimé");
                }
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
                if(liv!=null){
                    System.out.print(liv.getTitre()+" a été bien modifié");
                }
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
                List<Exemplaire> exmpls=exemplaireDao.getExemplaire();
                for (Exemplaire exmp : exmpls){
                    System.out.print("Num : "+exmp.getId() +" titre : " +exmp.getLivre().getTitre()+" auteur :" +exmp.getLivre().getAuteur()+"\n");
                }
                System.out.print("Entrer le numero du livre : ");
                exemplaire.setId(scanner.nextInt());

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
            case 8:
                System.out.println("Entrer le Numero de livre :");
                exemplaire.setId(scanner.nextInt());
                if(empruntDao.retourner(exemplaire)!=null){
                    System.out.println("Livre retourné à bibliothéque");
                }
                break;
            case 9:
                System.out.println("Rechercher un livre par titre ou auteur: ");
                scanner.nextLine();
                String searchWord=scanner.nextLine();
                List<Livre> listLivre=livreDao.recherchemultiple(searchWord);
                for (Livre l : listLivre){
                    System.out.print("isbn : "+l.getIsbn() +" titre : " +l.getTitre()+" auteur :" +l.getAuteur()+"\n");
                }
                break;
            case 10:
                System.out.println("-----Les statiqstiques du bibliothéque-----");
                ArrayList<Integer> stats=exemplaireDao.stats();
                System.out.println("Le nombre des livres disponibles : "+stats.get(0));
                System.out.println("Les livres disponibles : "+stats.get(1));
                System.out.println("Les livres empruntée : "+stats.get(2));
                System.out.println("Les livres perdus : "+stats.get(3));
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        while (choice!=10)
        {
            this.displayMenu();
            this.router();
        }
    }
}
