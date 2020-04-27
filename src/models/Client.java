package models;
import java.util.List;

public class Client extends Utilisateur{
    private int idClient;
    private String adresse;
    private String cni;
    private String telephone;
    private String email;
    private List<Compte> comptes;
    private Utilisateur user;

    public Client() {
    }

    public Client(String adresse, String cni, String telephone, String email, String nom, String prenom, String login, String password, String etat, Profil profil) {
        super(nom, prenom, login, password, etat, profil);
        this.adresse = adresse;
        this.cni = cni;
        this.telephone = telephone;
        this.email = email;
    }

    public Client(int idClient, String adresse, String cni, String telephone, String email, List<Compte> comptes, String nom, String prenom, String login, String password, String etat, Profil profil) {
        super(nom, prenom, login, password, etat, profil);
        this.idClient = idClient;
        this.adresse = adresse;
        this.cni = cni;
        this.telephone = telephone;
        this.email = email;
        this.comptes = comptes;
    }

    public Client(int idClient, String adresse, String cni, String telephone, String email, List<Compte> comptes, Utilisateur user, int id, String nom, String prenom, String login, String password, String etat, Profil profil) {
        super(id, nom, prenom, login, password, etat, profil);
        this.idClient = idClient;
        this.adresse = adresse;
        this.cni = cni;
        this.telephone = telephone;
        this.email = email;
        this.comptes = comptes;
        this.user = user;
    }

    public Client(String adresse, String cni, String telephone, String email, Utilisateur user) {
        this.adresse = adresse;
        this.cni = cni;
        this.telephone = telephone;
        this.email = email;
        this.user = user;
    }

    public Client(int idClient, String adresse, String cni, String telephone, String email, Utilisateur user) {
        this.idClient = idClient;
        this.adresse = adresse;
        this.cni = cni;
        this.telephone = telephone;
        this.email = email;
        this.user = user;
    }
    

    public Client(int idClient, String adresse, String cni, String telephone, String email, Utilisateur user, int id, String nom, String prenom, String login, String password, String etat, Profil profil) {
        super(id, nom, prenom, login, password, etat, profil);
        this.idClient = idClient;
        this.adresse = adresse;
        this.cni = cni;
        this.telephone = telephone;
        this.email = email;
        this.user = user;
    }

    public Client(String adresse, String cni, String telephone, String email, Utilisateur user, String nom, String prenom, String login, String password, String etat, Profil profil) {
        super(nom, prenom, login, password, etat, profil);
        this.adresse = adresse;
        this.cni = cni;
        this.telephone = telephone;
        this.email = email;
        this.user = user;
    }

    public Client(int idClient, String adresse, String cni, String telephone, String email, Utilisateur user, String nom, String prenom, String login, String password, String etat, Profil profil) {
        super(nom, prenom, login, password, etat, profil);
        this.idClient = idClient;
        this.adresse = adresse;
        this.cni = cni;
        this.telephone = telephone;
        this.email = email;
        this.user = user;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
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

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return ""+user;
    }
}
