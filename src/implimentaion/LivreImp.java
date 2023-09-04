package implimentaion;

import helper.DatabaseConnection;
import interfaces.LivreInterface;
import models.Livre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LivreImp implements LivreInterface {
    DatabaseConnection DB=DatabaseConnection.getInstance();
    Livre l = Livre.getInstance();
    @Override
    public Livre ajouter(Livre livre) {
        try {
            String insertSql = "INSERT INTO livre (isbn, titre,auteur,quantite) VALUES (?, ?,?,?)";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = DB.connect().prepareStatement(insertSql);
            preparedStatement.setString(1, livre.getIsbn());
            preparedStatement.setString(2, livre.getTitre());
            preparedStatement.setString(3, livre.getAuteur());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livre inséré avec succès.");
            } else {
                System.out.println("Aucun enregistrement inséré.");
            }
            preparedStatement.close();
            DB.disconnect();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return livre;
    }

    @Override
    public Livre supprimer(Livre livre) {
        try {
            String deleteSql = "DELETE FROM livre WHERE isbn = ?";
            PreparedStatement preparedStatement = DB.connect().prepareStatement(deleteSql);
            preparedStatement.setString(1, livre.getIsbn());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Livre supprimé avec succès.");
            } else {
                System.out.println("Aucun livre correspondant à cet ISBN.");
            }
            preparedStatement.close();
            DB.disconnect();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return livre;
    }

    @Override
    public Livre maj(Livre livre) {
        return null;
    }

    @Override
    public List<Livre> afficher() {
        List<Livre> livres = new ArrayList<>();
        try {
            String selectSql = "SELECT l.* FROM livre l INNER JOIN exemplaire e ON l.isbn = e.isbn WHERE e.statut LIKE 'Disponible'";
            PreparedStatement preparedStatement = DB.connect().prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                l.setIsbn(resultSet.getString("isbn"));
                l.setTitre(resultSet.getString("titre"));
                l.setAuteur(resultSet.getString("auteur"));
                livres.add(l);
            }
            resultSet.close();
            preparedStatement.close();
            DB.disconnect();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return livres;
    }

    @Override
    public Livre recherche(Livre livre) {
        try {
            String selectSql = "SELECT * FROM livre WHERE isbn like '"+livre.getIsbn()+"'";
            PreparedStatement preparedStatement = DB.connect().prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                l.setIsbn(resultSet.getString("isbn"));
                l.setTitre(resultSet.getString("titre"));
                l.setAuteur(resultSet.getString("auteur"));
            }
            resultSet.close();
            preparedStatement.close();
            DB.disconnect();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return l;
    }
}
