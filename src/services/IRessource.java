/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import models.*;

/**
 *
 * @author SARR
 */
public interface IRessource {
    public int creerUtilisateur(Utilisateur user);
    public int creerProfil(Profil profil);
    public int bloquerUtilisateur(Utilisateur user);
    public Utilisateur seConnecter(String login,String password);
    public int creerAgence(Agence agence);
    public int creerGuichet(Guichet guichet);
    public int affecterUserAgence(Affectation_agence affag);
    public int affecterCaissierGuichet(Affectation_guichet affg);
    public List<Profil> listeProfils();
    public List<Agence> listeAgences();
    public List<Guichet> listeGuichets();
    public List<Utilisateur> listeUtilisateurs();
    public List<Affectation_agence> listeAffectationAgences();
    public List<Affectation_guichet> listeAffectationGuichets();
    public Profil profilClient(int id);
    public Utilisateur chercherUtilisateur(String login);
    public List<Guichet> guichets_agence(Agence agence);
    public Agence recherchergenceParLibelle(String libelle);
}
