package models;
/**
 *
 * @author SARR
 */
public class Guichet {
    private int id;
    private int numero;
    private Agence agence;
    private String description;

    public Guichet() {
    }

    public Guichet(int numero, Agence agence, String description) {
        this.numero = numero;
        this.agence = agence;
        this.description = description;
    }

    public Guichet(int id, int numero, Agence agence, String description) {
        this.id = id;
        this.numero = numero;
        this.agence = agence;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return numero +" " + description;
    }

    
    
}
