package interfaces;
import java.util.List;

public interface LivreInterface {
    models.Livre ajouter();
    models.Livre supprimer();
    models.Livre maj();
    List<models.Livre> afficher();
    List<models.Livre> recherche();
}
