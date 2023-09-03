import helper.DatabaseConnection;
import helper.Menu;

public class Main {
    public static void main(String[] args) {

        /*DatabaseConnection obj = DatabaseConnection.getInstance();
        System.out.print(obj.connect());*/

        Menu menu=Menu.getInstance();
        menu.displayMenu();
        menu.router();
    }
}