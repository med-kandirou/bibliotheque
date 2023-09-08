package interfaces;

import dto.Emprunt;
import dto.Exemplaire;

public interface EmpruntInterface {

    Emprunt reserver(Emprunt emprunt);

    Exemplaire retourner(Exemplaire exmp);
}
