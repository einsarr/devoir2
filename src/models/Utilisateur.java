package models;
/**
 *
 * @author SARR
 */
public class Utilisateur {
    protected int idClient;
    protected String nom;
    protected String prenom;
    protected String login;
    protected String password;
    protected String etat;
    protected Profil profil;
    protected Agence agence;
    protected Guichet guichet;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String login, String password, String etat, Profil profil, Agence agence, Guichet guichet) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.etat = etat;
        this.profil = profil;
        this.agence = agence;
        this.guichet = guichet;
    }

    public Utilisateur(int idClient, String nom, String prenom, String login, String password, String etat, Profil profil, Agence agence, Guichet guichet) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.etat = etat;
        this.profil = profil;
        this.agence = agence;
        this.guichet = guichet;
    }
    
    public Utilisateur(int idClient, String nom, String prenom, String login, String password, String etat, Profil profil) {
        this.idClient = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.etat = etat;
        this.profil = profil;
    }

    public Utilisateur(String nom, String prenom, String login, String password, String etat, Profil profil) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.etat = etat;
        this.profil = profil;
    }

    public Utilisateur(String nom, String prenom, String login, String password, String etat, Profil profil, Agence agence) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.etat = etat;
        this.profil = profil;
        this.agence = agence;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }
    

    public int getId() {
        return idClient;
    }

    public void setId(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Guichet getGuichet() {
        return guichet;
    }

    public void setGuichet(Guichet guichet) {
        this.guichet = guichet;
    }
    

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    @Override
    public String toString() {
        return nom + " " + prenom;
    }
}
