package conversionTaux.session;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ConversionTauxAdminItf {
    String seConnecter(String login ,String passwd);
    String seDeconneceter();
    String consulterNBConnexion();
    List<String> top5Taux();
}
