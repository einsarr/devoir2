package services;

import dao.*;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;

public class RessourceService implements IRessource{
    UtilisateurDao udao;
    ProfilDao pdao;
    AgenceDao adao;
    GuichetDao gdao;
    Affectation_agenceDao affagdao;
    Affectation_guichetDao affgdao;
    public RessourceService(){
        udao = new UtilisateurDao(new DaoMysql());
        pdao = new ProfilDao(new DaoMysql());
        adao = new AgenceDao(new DaoMysql());
        gdao = new GuichetDao(new DaoMysql());
        affagdao = new Affectation_agenceDao(new DaoMysql());
        affgdao = new Affectation_guichetDao(new DaoMysql());
    }
    @Override
    public int creerUtilisateur(Utilisateur user) {
        return udao.create(user);
    }

    @Override
    public int creerProfil(Profil profil) {
        return pdao.create(profil);
    }

    @Override
    public int bloquerUtilisateur(Utilisateur user) {
        if(user.getEtat().compareToIgnoreCase("actif")==0)
        {
            user.setEtat("bloque");
        }
        else if(user.getEtat().compareToIgnoreCase("bloque")==0)
        {
            user.setEtat("actif");
        }
        return udao.bloquerUtilisateur(user);
    }
  //CRYPTER MOT DE PASSE
    private String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);
    }
    @Override
    public Utilisateur seConnecter(String login, String password) {
        Utilisateur user = new Utilisateur();
        try {
            user = udao.Seconnecter(login, encryptPassword(password));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RessourceService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RessourceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public int creerAgence(Agence agence) {
        return adao.create(agence);
    }

    @Override
    public int creerGuichet(Guichet guichet) {
        return gdao.create(guichet);
    }

    @Override
    public int affecterUserAgence(Affectation_agence aff) {
        return affagdao.create(aff);
    }

    @Override
    public int affecterCaissierGuichet(Affectation_guichet aff) {
        return affgdao.create(aff);
    }

    @Override
    public List<Profil> listeProfils() {
        return pdao.findAll();
    }

    @Override
    public List<Agence> listeAgences() {
        return adao.findAll();
    }

    @Override
    public List<Guichet> listeGuichets() {
        return gdao.findAll();
    }

    @Override
    public List<Utilisateur> listeUtilisateurs() {
        return udao.findAll();
    }

    @Override
    public List<Affectation_agence> listeAffectationAgences() {
        return affagdao.findAll();
    }

    @Override
    public List<Affectation_guichet> listeAffectationGuichets() {
        return affgdao.findAll();
    }
    
}
