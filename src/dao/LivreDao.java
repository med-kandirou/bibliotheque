package dao;

import helper.DatabaseConnection;
import interfaces.LivreInterface;
import dto.Livre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreDao implements LivreInterface {
    DatabaseConnection DB=DatabaseConnection.getInstance();

    private static LivreDao instance;
    public static LivreDao getInstance() {
        if (instance == null) {
            instance = new LivreDao();
        }
        return instance;
    }
    Livre l = new Livre();
    @Override
    public Livre ajouter(Livre livre) {
        try {
            String insertSql = "INSERT INTO livre (isbn, titre,auteur) VALUES (?, ?,?)";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = DB.connect().prepareStatement(insertSql);
            preparedStatement.setString(1, livre.getIsbn());
            preparedStatement.setString(2, livre.getTitre());
            preparedStatement.setString(3, livre.getAuteur());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return livre;
            }
            preparedStatement.close();
            DB.disconnect();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return null;
    }

    @Override
    public Livre supprimer(Livre livre) {
        try {
            String deleteSql = "DELETE FROM livre WHERE isbn = ?";
            PreparedStatement preparedStatement = DB.connect().prepareStatement(deleteSql);
            preparedStatement.setString(1, livre.getIsbn());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return livre;
            }
            preparedStatement.close();
            DB.disconnect();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return null;
    }

    @Override
    public Livre maj(Livre livre) {
        try {
            String updatequery = "UPDATE livre SET titre = ?, auteur = ? WHERE isbn = ?;";
            PreparedStatement preparedStatement = DB.connect().prepareStatement(updatequery);
            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setString(3, livre.getIsbn());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return livre;
            }
            preparedStatement.close();
            DB.disconnect();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Livre> afficher() {
        List<Livre> livres = new ArrayList<>();
        try {
            String selectSql = "SELECT l.* FROM livre l INNER JOIN exemplaire e ON l.isbn = e.isbn WHERE e.statut LIKE 'disponible'";
            PreparedStatement preparedStatement = DB.connect().prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Livre livre = new Livre();
                livre.setIsbn(resultSet.getString("isbn"));
                livre.setTitre(resultSet.getString("titre"));
                livre.setAuteur(resultSet.getString("auteur"));
                livres.add(livre);
            }
            resultSet.close();
            preparedStatement.close();
            DB.disconnect();
        }
        catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return livres;
    }


    @Override
    public Livre recherche(String isbn) {
        try {
            String selectSql = "SELECT * FROM livre WHERE isbn like '"+isbn+"'";
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
            return l;
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Livre> recherchemultiple(String searchword) {
        List<Livre> listLivre=new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM livre WHERE titre like '"+searchword+"' OR auteur like '"+searchword+"';";
            PreparedStatement preparedStatement = DB.connect().prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Livre livre=new Livre();
                livre.setIsbn(resultSet.getString("isbn"));
                livre.setTitre(resultSet.getString("titre"));
                livre.setAuteur(resultSet.getString("auteur"));
                listLivre.add(livre);
            }
            resultSet.close();
            preparedStatement.close();
            DB.disconnect();
            return listLivre;
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return null;
    }
}
