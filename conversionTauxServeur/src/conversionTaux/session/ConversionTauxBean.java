package conversionTaux.session;

import javax.ejb.*;
import conversionTaux.entity.*;

import javax.persistence.*;
;
/*
 Les indications fournies n'incluent pas ce qui relève de :
1) l'écriture d'un bean session
2) les éléments JPA, en particulier ceux relatifs à l'utilisation d'une entité dans une méthode.

 A vous de déterminer ce qui doit être écrit, en particulier pour la
 synchronisation des entités avec la BD
 */

@Stateless(mappedName= "ConversiontauxJNDI")
public class ConversionTauxBean implements ConversionTauxItf, ConversionTauxCste {


	@PersistenceContext(unitName="ConversionTauxPU") //declare dans lke persitence
	private EntityManager em;
	//EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConversionTauxPU");
	//EntityManager em = emf.createEntityManager();
	@Override
	public String convertir(String monnaieA, String monnaieB, double montant)
	{
			try {
				TauxEntity taux=(TauxEntity) em.createQuery
						("SELECT t FROM TauxEntity t WHERE t.monnaieA=:monnaieA and t.monnaieB=:monnaieB")
						.setParameter("monnaieA",monnaieA)
						.setParameter("monnaieB",monnaieB)
						.getSingleResult();
				return String.format(RESULTAT + "" + montant * taux.getTaux());
           /*
            Remonter de la BD en mémoire la valeur du taux correspondant à la paire (monnaieA, monnaieB)
            Retourner la String RESULTAT + montant * taux trouvé)
            */
			} catch (NoResultException e) //exception si la paire (monnaieA, monnaieB) n'est pas trouvee
			{
				return NO_RESULTAT;
			}
		catch (NonUniqueResultException e) //exception si plusieurs paires (monnaieA, monnaieB) sont trouvees
			{
				return RESULTAT_MULTIPLE;
			}
	}

	public String creerCompte(String login, String passwd)
	{
		try {
			AbonneEntity a = (AbonneEntity) em.createQuery("SELECT a FROM AbonneEntity a where a.login = :log")
					.setParameter("log",login)
					.getSingleResult();
			return NO_INSERTION;
		}
		catch (NoResultException nre){
			AbonneEntity a = new AbonneEntity(login, passwd);
			try{
				em.persist(a);
				return SUCCESS;
			}
			catch (EntityExistsException e) //exception si les valeurs existent deja
			{
				e.printStackTrace();
				return NO_INSERTION;
			}
		}
        catch(NonUniqueResultException e){
			return NO_INSERTION;
        }
	}
	/*
	@Override //premiere version
    public String creerCompte(String login, String passwd)
    {
		AbonneEntity abonneEntity=new AbonneEntity();
		abonneEntity.setLogin(login);
		abonneEntity.setPasswd(passwd);
        //Instancier l'entité
        try {
			//Persist entity
		    //em.getTransaction().begin(); pas besoin de ca
			em.persist(abonneEntity);
			//em.getTransaction().commit(); pas besoin de ca
            //La faire passer à l'état "managed" (cf le diagramme du cycle de vie d'une entité)
            return SUCCESS;
            }
        catch (EntityExistsException e) //exception si les valeurs existent deja
        {
            return NO_INSERTION;
        }
    }*/
	@Override
	public String supprimerCompte(int idAbonne)
	{
		//Instancier l'entité
		try {
			//La faire passer à l'état "managed" (cf le diagramme du cycle de vie d'une entité)
			Query query = em.createQuery("DELETE FROM AbonneEntity a WHERE a.idAbonne = :idAbonne ");
			query.setParameter("idAbonne", 1);
			query.executeUpdate();
			return SUCCESS;
		}
		catch (Exception e) //exception si les valeurs existent deja
		{
			System.out.println(e.getMessage());
			return NO_SUCCESS_DELETE;
		}
	}

}

