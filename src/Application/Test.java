package Application;
import models.*;
import dao.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import models.*;
import services.RessourceService;
import services.TransactionService;
/**
 * @author SARR
 */
public class Test {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        RessourceService r = new RessourceService();
        TransactionService t = new TransactionService();
        //ClientDao cdao = new ClientDao(new DaoMysql());
        /*GuichetDao gdao = new GuichetDao(new DaoMysql());
        CompteDao cdao = new CompteDao(new DaoMysql());
        List<Transaction> listeTrans = new ArrayList<>();
        Guichet guichet = gdao.findById(2);
        Compte compte = cdao.findById(1);
        Compte compte2 = cdao.findById(2);
        List<Compte> comptes = new ArrayList<>();
        comptes.add(compte);
        comptes.add(compte2);
        Transaction trans = new Transaction(300000, "Depot", guichet, compte);
        Transaction trans1 = new Transaction(20000, "Depot", guichet, compte);
        Transaction trans2 = new Transaction(20000, "Retrait", guichet, compte2);
        System.out.println(t.faireTransactionVir(trans1,trans2));
        */
        
        ClientDao cldao = new ClientDao(new DaoMysql());
        
        Client cl = cldao.findById(1);
        
        List<Transaction> listeT = t.listeTransactionsByClient(cl);
        
        for( Transaction value : listeT ) {
            System.out.println(value.toString());
        }
        
        /*Profil p = new Profil("Admin", "Administrateur");
        Utilisateur u = r.seConnecter("canfndeye", "canfndeye");
        System.out.println("ddj");
        System.out.println(u);*/
        
        //Compte cpt = t.rechercherCompteParNumero("NDCghgj2");
        
        /*Utilisateur user = new Utilisateur("SARR", "MOUUDDU", "sarr", "vddgg", "actif", profil);
        Client client = new Client("sdff", "dvdvvd", "vfvv", "hhf@fhfhf.dg", user);
        Compte cpt = new Compte("12233", 2000.30, new Date(), client, "ee");
        
        CompteCheck cptc = new CompteCheck(21000, cpt);
        
        System.out.println(t.creerCompte(cptc));*/
        
    }
    
    
   
}
