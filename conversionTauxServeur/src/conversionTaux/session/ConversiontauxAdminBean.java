package conversionTaux.session;

import conversionTaux.entity.AdminEntity;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful(mappedName = "ConversiontauxadminJNDI")
public class ConversiontauxAdminBean implements ConversionTauxAdminItf , ConversionTauxCste {

    private AdminEntity admin;
    @PersistenceContext(unitName="ConversionTauxPU")

    @EJB
    private ConversionSingletonItf refSingleton;
    EntityManager em;
    @Override
    public String seConnecter(String login, String passwd) {
        try {
            AdminEntity adm=(AdminEntity) em.createQuery("Select ad from AdminEntity ad where ad.login=:login and ad.passwd=:password")
                    .setParameter("login", login)
                    .setParameter("password", passwd)
                    .getSingleResult();
            this.admin=adm;

            return SUCCESS;
        }
        catch (NoResultException e){
            return  NO_RESULTAT;
        }
        catch (NonUniqueResultException e){
            return RESULTAT_MULTIPLE;
        }

    }

    @Override
    public String seDeconneceter() {
        try {
            this.admin = null;
            return DECONNEXION_;
        }catch (Exception e){
            return DECONNEXION_ERROR;
        }
    }

    @Override
    public String consulterNBConnexion() {
       if(this.admin!=null)
              return String.format("%d",refSingleton.getCmpt());
       return ACCES_DINED;
    }

    @Override
    public List<String> top5Taux() {
       /* SELECT count(f)AS nfois,taux.monnaieA,taux.monnaieB" +
        " from  TauxEntity.lesFavoris taux join atux.lesfavoris f  group by taux.monnaieA,taux.monnaieB order by nfois DESC*/
     List<String> result=   em.createQuery("SELECT count(f.idFavori) AS nfois,taux.monnaieA,taux.monnaieB" +
                " from  FavoriEntity f join f.leTaux taux group by taux.monnaieA,taux.monnaieB order by nfois DESC")
                .setMaxResults(5).getResultList();
      return result;
    }
}
