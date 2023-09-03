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
    @Override
    public Livre ajouter(Livre livre) {
        try {
            String insertSql = "INSERT INTO livre (isbn, titre,auteur,quantite) VALUES (?, ?,?,?)";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = DB.connect().prepareStatement(insertSql);
            preparedStatement.setString(1, livre.getIsbn());
            preparedStatement.setString(2, livre.getTitre());
            preparedStatement.setString(3, livre.getAuteur());
            preparedStatement.setInt(4, livre.getQuantite());
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
            String selectSql = "SELECT * FROM livre";
            PreparedStatement preparedStatement = DB.connect().prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            Livre l = Livre.getInstance();
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
    public List<Livre> recherche() {
        return null;
    }
}
