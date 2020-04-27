package models;
import java.util.Date;
import java.util.List;
/**
 *
 * @author SARR
 */
public class Transaction {
    private int id;
    private int montant;
    private Date createdAt;
    private String type;
    private Guichet guichet;
    private Compte compte;
    private List<Compte> lesComptes;

    public Transaction() {
    }

    public Transaction(int montant, String type, Guichet guichet, Compte compte) {
        this.montant = montant;
        this.type = type;
        this.guichet = guichet;
        this.compte = compte;
    }
    

    public Transaction(int montant, String type, Compte compte) {
        this.montant = montant;
        this.type = type;
        this.compte = compte;
    }
    public Transaction(int montant, String type, List<Compte> comptes) {
        this.montant = montant;
        this.type = type;
        this.lesComptes = comptes;
    }

    public Transaction(int montant, String type, Guichet guichet, List<Compte> lesComptes) {
        this.montant = montant;
        this.type = type;
        this.guichet = guichet;
        this.lesComptes = lesComptes;
    }
    

    public Transaction(int id, int montant, String type, Guichet guichet, List<Compte> lesComptes) {
        this.id = id;
        this.montant = montant;
        this.type = type;
        this.guichet = guichet;
        this.lesComptes = lesComptes;
    }
    

    public List<Compte> getLesComptes() {
        return lesComptes;
    }
    

    public Transaction(int id, int montant, Date createdAt, String type, Guichet guichet, Compte compte) {
        this.id = id;
        this.montant = montant;
        this.createdAt = createdAt;
        this.type = type;
        this.guichet = guichet;
        this.compte = compte;
    }

    public Transaction(int montant, Date createdAt, String type, Guichet guichet, Compte compte) {
        this.montant = montant;
        this.createdAt = createdAt;
        this.type = type;
        this.guichet = guichet;
        this.compte = compte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Guichet getGuichet() {
        return guichet;
    }

    public void setGuichet(Guichet guichet) {
        this.guichet = guichet;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", montant=" + montant + ", createdAt=" + createdAt + ", type=" + type + ", guichet=" + guichet + ", compte=" + compte + '}';
    }
    
}
