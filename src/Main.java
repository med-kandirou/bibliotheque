import helper.DatabaseConnection;
import helper.Menu;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Menu menu=Menu.getInstance();
        menu.displayMenu();
        menu.router();
    }
}