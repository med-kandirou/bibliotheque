package interfaces;
import models.Livre;

import java.sql.SQLException;
import java.util.List;

public interface LivreInterface {
    Livre ajouter(Livre livre);
    Livre supprimer(Livre livre);
    Livre maj(Livre livre);
    List<Livre> afficher();
    Livre recherche(String isbn);
}
