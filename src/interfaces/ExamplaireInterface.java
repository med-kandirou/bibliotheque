package interfaces;

import dto.Exemplaire;
import dto.Livre;

import java.util.List;

public interface ExamplaireInterface {
    Exemplaire reserver();

    Exemplaire ajouter(Exemplaire exmp);

}
