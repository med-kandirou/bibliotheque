package helper;

import models.Livre;

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
        System.out.println("2. ajouter un exemplaire :");
        System.out.println("3. Option 3");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // Handle user choices
    public void router() {
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Livre livre = Livre.getInstance();

                System.out.print("Entrer ISBN: ");
                livre.setIsbn(scanner.next());

                System.out.print("Entrer le titre: ");
                scanner.nextLine(); // Pour consommer le caractère de nouvelle ligne restant
                livre.setTitre(scanner.nextLine());

                System.out.print("Entrer Auteur: ");
                livre.setAuteur(scanner.nextLine());

                System.out.print("Entrer Quantity: ");
                livre.setQuantite(scanner.nextInt());


                //System.out.print(livre.ajouter());
                break;
            case 2:
                System.out.println("Option 2 selected.");
                break;
            case 3:
                System.out.println("Option 3 selected.");
                break;
            case 4:
                System.out.println("Exiting the application.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
