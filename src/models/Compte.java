package models;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
/**
 *
 * @author SARR
 */
public class Compte {
    protected int id;
    protected String numero;
    protected float solde;
    protected Date createdAt;
    protected Client client;
    protected String etat;
    protected List<Transaction> transactions;

    public Compte() {
    }

    public Compte(int id, String numero, float solde, Client client, String etat) {
        this.id = id;
        this.numero = numero;
        this.solde = solde;
        this.client = client;
        this.etat = etat;
    }

    public Compte(Client client,String etat) {
        this.client = client;
        this.etat = etat;
    }
    

    public Compte(int id, String numero, float solde, Date createdAt, Client client, List<Transaction> transactions,String etat) {
        this.id = id;
        this.numero = numero;
        this.solde = solde;
        this.createdAt = createdAt;
        this.client = client;
        this.transactions = transactions;
        this.etat = etat;
    }
    public void virement(Compte cpt1,Compte cpt2,int montant)
    {
        cpt1.retrait(montant);
        cpt2.versement(montant);
    }
    public void retrait(int montant)
    {
        this.solde-=montant;
    }
    public void versement(int montant)
    {
        this.solde +=montant;
    }

    public Compte(int id, String numero, float solde, Date createdAt, Client client,String etat) {
        this.id = id;
        this.numero = numero;
        this.solde = solde;
        this.createdAt = createdAt;
        this.client = client;
        this.etat = etat;
    }

    public Compte(String numero, float solde, Date createdAt, Client client,String etat) {
        this.numero = numero;
        this.solde = solde;
        this.createdAt = createdAt;
        this.client = client;
        this.etat = etat;
    }

    public Compte(int solde, Client client,String etat) {
        this.solde = solde;
        this.client = client;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return numero;
    }
}
