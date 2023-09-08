package dao;

import dto.Emprunt;
import dto.Emprunteur;
import dto.Livre;
import helper.DatabaseConnection;
import interfaces.EmpruntInterface;
import interfaces.EmprunteurInterface;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;

public class EmpruntDao implements EmpruntInterface {

    DatabaseConnection DB=DatabaseConnection.getInstance();

    public EmpruntDao() {
    }

    private static EmpruntDao instance;
    public static EmpruntDao getInstance() {
        if (instance == null) {
            instance = new EmpruntDao();
        }
        return instance;
    }
    @Override
    public Emprunt reserver(Emprunt emprunt) {
        try {
            String insertSql = "INSERT INTO emprunt (emprunteur_id, exemplaire_id,date_emprunt,date_retour) VALUES (?, ?,?,?)";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = DB.connect().prepareStatement(insertSql);
            preparedStatement.setInt(1, emprunt.getEmprunteur().getNum());
            preparedStatement.setInt(2, emprunt.getExemplaire().getId());
            LocalDate currentDate = LocalDate.now();
            preparedStatement.setDate(3,java.sql.Date.valueOf(currentDate));
            preparedStatement.setDate(4, java.sql.Date.valueOf(emprunt.getDateRetour().toString()));
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return emprunt;
            }
            preparedStatement.close();
            DB.disconnect();
        }
        catch (Exception e){
            System.out.print(e.getMessage());
        }
        return null;
    }
}
