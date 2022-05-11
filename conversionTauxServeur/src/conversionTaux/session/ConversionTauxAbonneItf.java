package conversionTaux.session;

import javax.ejb.Remote;


@Remote
public interface ConversionTauxAbonneItf {
     double convertir(String favori, double montant);
     String connecter(String login, String passwd) ;
     String deconnecter() ;
     String ajouterFavori(String favori, String monnaieA, String monnaieB) ;
     String listerFavoris(String login );

}
