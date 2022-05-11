package conversionTaux.session;

import javax.ejb.Remote;

@Remote
public interface ConversionTauxItf {
	  String convertir(String monnaieA, String monnaieB, double montant);
      String creerCompte(String login, String passwd);
      String supprimerCompte(int idAbonne);

}
