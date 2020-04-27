package models;
import java.util.Date;
import java.util.List;
/**
 *
 * @author SARR
 */
public class CompteCheck extends Compte{
    private int id_cpt_ch;
    private int frais;
    private Compte compte;

    public CompteCheck() {
    }

    

    public CompteCheck(int id_cpt_ch, int frais, Compte compte) {
        this.id_cpt_ch = id_cpt_ch;
        this.frais = frais;
        this.compte = compte;
    }

    public CompteCheck(int frais, Compte compte) {
        this.frais = frais;
        this.compte = compte;
    }

    public CompteCheck(int frais, Compte compte, int solde, Client client, String etat) {
        super(solde, client, etat);
        this.frais = frais;
        this.compte = compte;
    }

    public CompteCheck(int frais, Compte compte, Client client, String etat) {
        super(client, etat);
        this.frais = frais;
        this.compte = compte;
    }

    public CompteCheck(int id_cpt_ch, int frais, Compte compte, Client client, String etat) {
        super(client, etat);
        this.id_cpt_ch = id_cpt_ch;
        this.frais = frais;
        this.compte = compte;
    }
    

    public CompteCheck(int id_cpt_ch, int frais, Compte compte, String numero, int solde, Date createdAt, Client client, String etat) {
        super(numero, solde, createdAt, client, etat);
        this.id_cpt_ch = id_cpt_ch;
        this.frais = frais;
        this.compte = compte;
    }

    public CompteCheck(int frais, Compte compte, String numero, int solde, Date createdAt, Client client, String etat) {
        super(numero, solde, createdAt, client, etat);
        this.frais = frais;
        this.compte = compte;
    }

    public CompteCheck(int id_cpt_ch, int frais, Compte compte, int id, String numero, int solde, Date createdAt, Client client, String etat) {
        super(id, numero, solde, createdAt, client, etat);
        this.id_cpt_ch = id_cpt_ch;
        this.frais = frais;
        this.compte = compte;
    }

    

    public CompteCheck(int id_cpt_ch, int frais, Compte compte, int solde, Client client,String etat) {
        super(solde, client,etat);
        this.id_cpt_ch = id_cpt_ch;
        this.frais = frais;
        this.compte = compte;
    }

    public CompteCheck(int id_cpt_ch, int frais, Compte compte, int id, String numero, int solde, Date createdAt, Client client, List<Transaction> transactions,String etat) {
        super(id, numero, solde, createdAt, client, transactions,etat);
        this.id_cpt_ch = id_cpt_ch;
        this.frais = frais;
        this.compte = compte;
    }

    public int getId_cpt_ch() {
        return id_cpt_ch;
    }

    public void setId_cpt_ch(int id_cpt_ch) {
        this.id_cpt_ch = id_cpt_ch;
    }

    public int getFrais() {
        return frais;
    }

    public void setFrais(int frais) {
        this.frais = frais;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    @Override
    public String toString() {
        return "CompteCheck{" + "id_cpt_ch=" + id_cpt_ch + ", frais=" + frais + ", compte=" + compte + '}';
    }
    
    
}
