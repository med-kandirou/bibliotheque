package helper;

import implimentaion.LivreImp;
import classes.Livre;

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
        System.out.println("5. Emprunter un livre :");
        System.out.println("6. Stattistique du bibliotheque :");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }


    Livre livre = Livre.getInstance();
    LivreImp livreImp=new LivreImp();
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
                System.out.print(livreImp.ajouter(livre));
                this.displayMenu();
                this.router();
                break;
            case 2:
                System.out.println("Entrer le ISBN du livre :");
                livre.setIsbn(scanner.next());
                System.out.print(livreImp.supprimer(livre));
                this.displayMenu();
                this.router();
                break;
            case 3:
                System.out.println("Entrer le ISBN du livre a modifier :");
                String isbn=scanner.next();
                Livre liv=livreImp.recherche(isbn);
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
                liv=livreImp.maj(liv);
                System.out.print(liv.getTitre()+" a été bien modifié");
                break;
            case 4:
                System.out.println("La liste des livres diponibles :");
                List<Livre> livres= livreImp.afficher();
                for (Livre l : livres){
                    System.out.print("isbn : "+l.getIsbn() +" titre :" +l.getTitre()+" auteur :" +l.getAuteur()+"\n");
                }
                break;
            case 5:
                System.out.print("Entrer Le nom Complet : ");
                livre.setIsbn(scanner.next());
                System.out.print("Entrer le titre: ");
                scanner.nextLine();
                livre.setTitre(scanner.nextLine());
                System.out.print("Entrer Auteur: ");
                livre.setAuteur(scanner.nextLine());
                System.out.print(livreImp.ajouter(livre));
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
