package interfaces;


import dto.Emprunteur;

import java.util.List;

public interface EmprunteurInterface {

    List<Emprunteur> getemprunteur();
    Emprunteur ajouter(Emprunteur emp);
    Emprunteur supprimer();
}
