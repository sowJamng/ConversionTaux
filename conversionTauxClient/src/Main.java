import javax.naming.InitialContext;
import java.util.*;
public class Main {
public static void main(String[] args) throws Exception {
        try {
            InitialContext ctx = new InitialContext();
            conversionTaux.session.ConversionTauxItf c =
                    (conversionTaux.session.ConversionTauxItf) ctx.lookup("ConversiontauxJNDI");

            conversionTaux.session.ConversionTauxAbonneItf c2 =
                    (conversionTaux.session.ConversionTauxAbonneItf) ctx.lookup("ConversiontauxaJNDI");
            conversionTaux.session.ConversionTauxAdminItf c3 =
                    (conversionTaux.session.ConversionTauxAdminItf) ctx.lookup("ConversiontauxadminJNDI");

            Scanner mylistchoice = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Bienvenue chere utilisateur ");
            System.out.println("Veullez choisir une option de votre choix");
            System.out.println("1=>Creer un compte");
            System.out.println("2=>Supprimer un compte ");
            System.out.println("3=>Convertir un taux");
            System.out.println("4=>Se connecter");
            System.out.println("5=>Se deconneceter");
            System.out.println("6=>Ajouter  un Favorie pour abonne connecte");
            System.out.println("7=>Conversion abonné connecté");
            System.out.println("--------partie Admin------------");
            System.out.println("8=>Connexion Admin");
            System.out.println("9=>Consulter le nombre d'abonnes connectes");
            System.out.println("10=>Consulter le Top 5 des favories");
            System.out.println("0=> pour quitter");
            System.out.println("Enter votre choix");
            String choix = mylistchoice.nextLine();
            System.out.println("votre choix:"+Integer.valueOf(choix));
            switch ( choix){
                case "1":
                    c.creerCompte("maodosow@gmail.com", "123");
                    break;
                case "2":
                    c.supprimerCompte(1);
                    break;
                case "0":
                    System.out.println("aurevoir ");
                case "4":
                    c2.connecter("monk","sow");
                case "5":
                    c2.deconnecter();
                case "6":
                    c2.ajouterFavori("Euro_CFA","euros","dollars");
                case "7":
                    c2.convertir("Euros_Yen",100.0);
             /*   case 8:
                    c3.seConnecter("monk","sow");
                case 9:
                    c3.consulterNBConnexion();*/
                case "10":
                    c3.top5Taux();
                default:
                    c.creerCompte("maodosow@gmail.com", "123");
                    //System.out.println("Au revoir  à la prochaine ");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
