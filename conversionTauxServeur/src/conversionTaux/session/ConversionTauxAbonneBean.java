package conversionTaux.session;

import conversionTaux.entity.AbonneEntity;
import conversionTaux.entity.FavoriEntity;
import conversionTaux.entity.TauxEntity;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateful;

@Stateful(mappedName= "ConversiontauxaJNDI")
public class ConversionTauxAbonneBean implements ConversionTauxAbonneItf, ConversionTauxCste {

    public AbonneEntity abonneEntity;
    @PersistenceContext(unitName="ConversionTauxPU")
    private EntityManager em;
    @EJB
    private ConversionSingletonItf refSingleton;

    @Override
    public String connecter(String login, String passwd) {
        try {
            this.abonneEntity = (AbonneEntity)
                    em.createQuery("select a from AbonneEntity a WHERE a.login=:login and a.passwd=:passwd")
                            .setParameter("login",login)
                            .setParameter("passwd",passwd)
                            .getSingleResult();
              refSingleton.setCmpt();
            return SUCCESS;
        } catch (NonUniqueResultException e) {
            return CONNEXION_ERROR;
        }
        catch (NoResultException e) {
            return CONNEXION_ERROR;
        }
    }
    @Override
    public   String deconnecter(){
        try {
            this.abonneEntity = null;
            return  DECONNEXION_;
        }
        catch (Exception e){
            e.printStackTrace();
            return DECONNEXION_ERROR;
        }

    }

    @Override
    public double convertir(String favori, double montant){
        try {
            double covt = 0.0;
            if(this.abonneEntity!=null)
            {
                Optional<FavoriEntity> favoriEntity = this.abonneEntity.getFavoris().stream().
                        filter(f -> f.getLibelleFavori().equals(favori)).findFirst();

                if (favoriEntity.isPresent()){
                    TauxEntity leTaux=favoriEntity.get().getTauxEntity();
                    if (leTaux!=null)
                          covt = leTaux.getTaux() * montant;
                }
            }
            return covt;
        }
        catch (NoResultException e){
            e.printStackTrace();
            System.out.println(ACCES_DINED);
            return 0.0;
        }
    }

    @Override
    public String ajouterFavori(String favori, String monnaieA, String monnaieB) {
        try {
            if (this.abonneEntity != null) //optimale
            {
                this.abonneEntity = em.merge(abonneEntity);
                em.refresh(abonneEntity);
                try {
                    //a revoir
                     em.createQuery
                                    ("SELECT f FROM  AbonneEntity  a join a.lesFavoris f WHERE f.libelleFavori=:libelleFavorie and a.idAbonne=:ab")
                            .setParameter("libelleFavorie", favori)
                            .setParameter("ab", abonneEntity.getIdAbonne())
                            .getSingleResult();
                    FavoriEntity favoriEntity = new FavoriEntity();
                    TauxEntity taux = (TauxEntity) em.createQuery
                                    ("SELECT t FROM TauxEntity t  WHERE t.monnaieA=:monnaieA and t.monnaieB=:monnaieB")
                            .setParameter("monnaieA", monnaieA)
                            .setParameter("monnaieB", monnaieB).getSingleResult();
                    favoriEntity.setTauxEntity(taux);
                    favoriEntity.setLibelleFavori(favori);
                    em.persist(favoriEntity);
                    this.abonneEntity.getFavoris().add(favoriEntity);
                    return AJOUT_SUCCESS;
                } catch (NonUniqueResultException e) {
                    e.printStackTrace();
                    return RESULTAT_MULTIPLE;
                }
                catch(NoResultException e){
                    return NO_RESULTAT;
                }
            }

            else
                       return ACCES_DINED;
        }
        catch (NoResultException e){
            e.printStackTrace();
            return NO_RESULTAT;
        }

    }
    @Override
    public String listerFavoris(String login ) {
       try{
          AbonneEntity abone=(AbonneEntity) em.createQuery("select a from AbonneEntity  a where a.login=:login").setParameter("login",login)
                   .getSingleResult();

           StringBuffer b=new StringBuffer();
           try {
               List<FavoriEntity> favoris = (List<FavoriEntity>) em.createQuery("select a.lesFavoris from AbonneEntity a where a.idAbonne=:id ")
                       .setParameter("id", abone.getIdAbonne())
                       .getResultList();
               if(!favoris.isEmpty()){
                   favoris.forEach(favori->{
                       TauxEntity taux=favori.getTauxEntity();
                       if(taux!=null)
                           b.append(abone.getLogin()).append(" libelle:").append(favori.getLibelleFavori()).append("monnaieA: ")
                                   .append(taux.getMonnaieA()).append(" monnaieB:").append(taux.getMonnaieB())
                                   .append("taux:").append(taux.getTaux());
                   });

               }
                 return b.toString();
           }
           catch (NoResultException e){
               return NO_RESULTAT;
           }

       }
       catch (NonUniqueResultException e){
           return RESULTAT_MULTIPLE;
       }
       catch (NoResultException e){
           return NO_RESULTAT;
       }


    }
}
