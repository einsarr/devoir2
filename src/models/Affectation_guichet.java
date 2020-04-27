package models;
import java.util.Date;
/**
 *
 * @author SARR
 */
public class Affectation_guichet {
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private Utilisateur caissier;
    private Guichet guichet;
    private Agence agence;

    public Affectation_guichet(int id, Date dateDebut, Date dateFin, Utilisateur caissier, Guichet guichet) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.caissier = caissier;
        this.guichet = guichet;
    }

    public Affectation_guichet(int id, Date dateDebut, Date dateFin, Utilisateur caissier, Guichet guichet, Agence agence) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.caissier = caissier;
        this.guichet = guichet;
        this.agence = agence;
    }

    public Affectation_guichet(int id, Date dateDebut, Utilisateur caissier, Guichet guichet, Agence agence) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.caissier = caissier;
        this.guichet = guichet;
        this.agence = agence;
    }
    

    public Affectation_guichet(Utilisateur caissier, Guichet guichet) {
        this.caissier = caissier;
        this.guichet = guichet;
    }

    public Affectation_guichet() {
    }

    public Affectation_guichet(Date dateDebut, Date dateFin, Utilisateur caissier, Guichet guichet) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.caissier = caissier;
        this.guichet = guichet;
    }

    public Affectation_guichet(int id, Date dateDebut, Utilisateur caissier, Guichet guichet) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.caissier = caissier;
        this.guichet = guichet;
    }

    public Affectation_guichet(Utilisateur caissier, Guichet guichet, Agence agence) {
        this.caissier = caissier;
        this.guichet = guichet;
        this.agence = agence;
    }

    public Utilisateur getCaissier() {
        return caissier;
    }

    public void setCaissier(Utilisateur caissier) {
        this.caissier = caissier;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Utilisateur getUser() {
        return caissier;
    }

    public void setUser(Utilisateur caissier) {
        this.caissier = caissier;
    }

    public Guichet getGuichet() {
        return guichet;
    }

    public void setGuichet(Guichet guichet) {
        this.guichet = guichet;
    }

    @Override
    public String toString() {
        return "Affectation{" + "id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", caissier=" + caissier + ", guichet=" + guichet + '}';
    }
    
}
