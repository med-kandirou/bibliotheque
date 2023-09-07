package dao;

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


}
