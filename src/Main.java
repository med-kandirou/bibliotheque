import helper.DatabaseConnection;
import helper.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu=Menu.getInstance();
        menu.displayMenu();
        menu.router();
    }
}