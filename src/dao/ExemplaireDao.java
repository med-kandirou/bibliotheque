package dao;

import com.mysql.cj.jdbc.MysqlDataSourceFactory;
import dto.Livre;
import helper.DatabaseConnection;
import interfaces.ExamplaireInterface;
import dto.Exemplaire;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ExemplaireDao implements ExamplaireInterface {

    DatabaseConnection DB=DatabaseConnection.getInstance();

    private static ExemplaireDao instance;
    public static ExemplaireDao getInstance() {
        if (instance == null) {
            instance = new ExemplaireDao();
        }
        return instance;
    }
    @Override
    public Exemplaire reserver() {
        return null;
    }

    @Override
    public Exemplaire ajouter(Exemplaire exmp) {
        try {
            String insertSql = "INSERT INTO exemplaire (isbn) VALUES (?)";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = DB.connect().prepareStatement(insertSql);
            preparedStatement.setString(1, exmp.getLivre().getIsbn());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return exmp;
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
    public List<Exemplaire> getExemplaire() {
        List<Exemplaire> exemplaires = new ArrayList<>();
        try {
            String selectSql = "SELECT e.*,l.* FROM exemplaire e INNER JOIN livre l ON l.isbn = e.isbn WHERE e.statut LIKE 'disponible';";
            PreparedStatement preparedStatement = DB.connect().prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Exemplaire exmp = new Exemplaire();
                Livre l=new Livre();
                l.setIsbn(resultSet.getString("isbn"));
                l.setTitre(resultSet.getString("titre"));
                l.setAuteur(resultSet.getString("auteur"));
                exmp.setId(resultSet.getInt("id"));
                exmp.setLivre(l);
                exemplaires.add(exmp);
            }
            resultSet.close();
            preparedStatement.close();
            DB.disconnect();
            return exemplaires;
        }
        catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Integer> stats() {
        ArrayList<Integer> totals = new ArrayList<>();
        try {
            String selectSql = "SELECT " +
                    "(SELECT COUNT(*) FROM exemplaire WHERE statut LIKE 'disponible') AS total_dispo, " +
                    "(SELECT COUNT(*) FROM exemplaire WHERE statut LIKE 'emprunté') AS total_emprunte, " +
                    "(SELECT COUNT(*) FROM exemplaire WHERE statut LIKE 'perdu') AS total_perdu";

            PreparedStatement preparedStatement = DB.connect().prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                totals.add(resultSet.getInt("total_dispo"));
                totals.add(resultSet.getInt("total_emprunte"));
                totals.add(resultSet.getInt("total_perdu"));
            }
            resultSet.close();
            preparedStatement.close();
            DB.disconnect();

            return totals;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



}
