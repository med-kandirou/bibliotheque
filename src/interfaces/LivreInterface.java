package interfaces;
import dto.Livre;

import java.util.List;

public interface LivreInterface {
    Livre ajouter(Livre livre);
    Livre supprimer(Livre livre);
    Livre maj(Livre livre);
    List<Livre> afficher();
    Livre recherche(String isbn);
}
