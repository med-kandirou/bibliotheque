package dao;

import dto.Emprunteur;
import dto.Livre;
import helper.DatabaseConnection;
import interfaces.EmprunteurInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmprunteurDao implements EmprunteurInterface {

    DatabaseConnection DB=DatabaseConnection.getInstance();

    Emprunteur emp=new Emprunteur();

    private static EmprunteurDao instance;
    public static EmprunteurDao getInstance() {
        if (instance == null) {
            instance = new EmprunteurDao();
        }
        return instance;
    }
    @Override
    public List<Emprunteur> getemprunteur() {
        List<Emprunteur> emprntrs = new ArrayList<>();
        try {
            String selectSql = "SELECT * FROM emprunteur";
            PreparedStatement preparedStatement = DB.connect().prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Emprunteur instace= new Emprunteur();
                instace.setNum(resultSet.getInt("num"));
                instace.setNom(resultSet.getString("nom"));
                instace.setPrenom(resultSet.getString("prenom"));
                instace.setPrenom(resultSet.getString("email"));
                instace.setPrenom(resultSet.getString("tele"));
                emprntrs.add(instace);
            }
            resultSet.close();
            preparedStatement.close();
            DB.disconnect();
        }
        catch (SQLException e){
            System.out.print(e.getMessage());
        }
        return emprntrs;
    }

    @Override
    public Emprunteur ajouter(Emprunteur emp) {
        try {
            String insertSql = "INSERT INTO emprunteur (nom,prenom,email,tele) VALUES (?, ?,?,?)";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = DB.connect().prepareStatement(insertSql);
            preparedStatement.setString(1, emp.getNom());
            preparedStatement.setString(2, emp.getPrenom());
            preparedStatement.setString(3, emp.getEmail());
            preparedStatement.setString(4, emp.getTele());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return emp;
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
    public Emprunteur supprimer() {
        return null;
    }
}
