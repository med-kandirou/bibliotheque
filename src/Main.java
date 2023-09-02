import helper.DatabaseConnection;
import helper.Menu;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        /*DatabaseConnection obj = DatabaseConnection.getInstance();
        System.out.print(obj.connect());*/

        Menu menu=Menu.getInstance();
        menu.displayMenu();
        menu.router();
    }
}