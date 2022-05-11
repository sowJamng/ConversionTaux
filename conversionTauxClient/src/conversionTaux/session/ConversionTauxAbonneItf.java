package conversionTaux.session;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ConversionTauxAbonneItf {
     String connecter(String login, String passwd) ;
     String deconnecter() ;
      String ajouterFavori(String favori, String monnaieA, String monnaieB) ;
     double convertir(String favori, double montant);
    String listerFavoris(String login );
}
