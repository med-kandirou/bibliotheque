package interfaces;

import dto.Exemplaire;
import dto.Livre;

import java.util.ArrayList;
import java.util.List;

public interface ExamplaireInterface {
    Exemplaire ajouter(Exemplaire exmp);

    List<Exemplaire> getExemplaire();

    ArrayList<Integer> stats();

}
