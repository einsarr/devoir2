package models;
import java.util.Date;
import java.util.List;
/**
 *
 * @author SARR
 */
public class CompteEpargne extends Compte{
    private int id_cpt_ep;
    private float taux;
    private Date dateDebut;
    private Date dateFin;
    private Compte compte;

    public CompteEpargne() {
    }

    public CompteEpargne(int id_cpt_ep, float taux, Compte compte) {
        this.id_cpt_ep = id_cpt_ep;
        this.taux = taux;
        this.compte = compte;
    }

    public CompteEpargne(float taux, Compte compte) {
        this.taux = taux;
        this.compte = compte;
    }

    public CompteEpargne(float taux, Compte compte, Client client, String etat) {
        super(client, etat);
        this.taux = taux;
        this.compte = compte;
    }
    

    public CompteEpargne(float taux, Date dateDebut, Date dateFin, Compte compte, String numero, int solde, Date createdAt, Client client, String etat) {
        super(numero, solde, createdAt, client, etat);
        this.taux = taux;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.compte = compte;
    }
    

    public CompteEpargne(int id_cpt_ep, float taux, Date dateDebut, Date dateFin, Compte compte, int solde, Client client, String etat) {
        super(solde, client, etat);
        this.id_cpt_ep = id_cpt_ep;
        this.taux = taux;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.compte = compte;
    }
    

    

    public int getId_cpt_ep() {
        return id_cpt_ep;
    }

    public void setId_cpt_ep(int id_cpt_ep) {
        this.id_cpt_ep = id_cpt_ep;
    }

    public float getTaux() {
        return taux;
    }

    public void setTaux(float taux) {
        this.taux = taux;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    @Override
    public String toString() {
        return "CompteEpargne{" + "id_cpt_ep=" + id_cpt_ep + ", taux=" + taux + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", compte=" + compte + '}';
    }
    
    
}
