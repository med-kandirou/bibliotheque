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


}
