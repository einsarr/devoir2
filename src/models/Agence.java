package models;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SARR
 */
public class Agence {
    private int id;
    private String libelle;
    private String telephone;
    private String email;
    private String adresse;
    private Date createdAt;

    private List<Guichet> guichets;
    private List<Utilisateur> utilisateurs;

    public Agence() {
    }

    public Agence(int id, String libelle, String telephone, String email, String adresse, Date createdAt, List<Guichet> guichets, List<Utilisateur> utilisateurs) {
        this.id = id;
        this.libelle = libelle;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
        this.createdAt = createdAt;
        this.guichets = guichets;
        this.utilisateurs = utilisateurs;
    }

    public Agence(int id, String libelle, String telephone, String email, String adresse) {
        this.id = id;
        this.libelle = libelle;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    public Agence(String libelle, String telephone, String email, String adresse) {
        this.libelle = libelle;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Guichet> getGuichets() {
        return guichets;
    }

    public void setGuichets(List<Guichet> guichets) {
        this.guichets = guichets;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return libelle;
    }

    
    
}
