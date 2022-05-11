package conversionTaux.session;
import javax.ejb.Remote;
import java.util.List;
import java.util.Map;

@Remote
public interface ConversionTauxItf {
	 String convertir(String monnaieA, String monnaieB, double montant);
     String creerCompte(String login, String passwd);
    String  supprimerCompte(int idAbonne);



}
