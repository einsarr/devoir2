package models;

import java.util.Date;

/**
 *
 * @author SARR
 */
public class Affectation_agence {
    private int id;
    private Agence agence;
    private Utilisateur user;
    private Date dateDebut;
    private Date dateFin;

    public Affectation_agence() {
    }

    public Affectation_agence(Agence agence, Utilisateur user) {
        this.agence = agence;
        this.user = user;
    }

    public Affectation_agence(int id, Agence agence, Utilisateur user, Date dateDebut, Date dateFin) {
        this.id = id;
        this.agence = agence;
        this.user = user;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Affectation_agence(Agence agence, Utilisateur user, Date dateDebut, Date dateFin) {
        this.agence = agence;
        this.user = user;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Affectation_agence(int id, Agence agence, Utilisateur user, Date dateFin) {
        this.id = id;
        this.agence = agence;
        this.user = user;
        this.dateFin = dateFin;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Affectation_agence{" + "id=" + id + ", agence=" + agence + ", user=" + user + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + '}';
    }
    
    
}
