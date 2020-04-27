package dao;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
public class UtilisateurDao implements IDao<Utilisateur> {
    private final String SQL_INSERT  = "INSERT INTO utilisateur VALUES(null,?,?,?,?,?,?,?)";
    private final String SQL_FINDALL = "SELECT * FROM utilisateur";
    private final String SQL_UPDATE  = "UPDATE utilisateur SET libelle=?,telephone=?,email=?"
            + ",adresse=? WHERE id=?";
    private final String SQL_FIND    = "SELECT * FROM utilisateur WHERE id=?";
    private final String SQL_SE_CONNECTER    = "SELECT * FROM utilisateur WHERE login=? AND password=?";
    private final String SQL_BLOQUER_USER    = "UPDATE utilisateur SET etat=? WHERE id=?";
    private ISGBD mysql;
    private ProfilDao pdao;
    private AgenceDao adao;
    public UtilisateurDao(ISGBD mysql){
        this.mysql = mysql;
        pdao = new ProfilDao(mysql);
        adao = new AgenceDao(mysql);
    }
    public int bloquerUtilisateur(Utilisateur objet)
    {
        mysql.initPS(SQL_BLOQUER_USER);
        int id=0;
        try {
            mysql.getPstm().setString(1,objet.getEtat());
            mysql.getPstm().setInt(2,objet.getId());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }
    public Utilisateur Seconnecter(String login,String password) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        mysql.initPS(SQL_SE_CONNECTER);
        Utilisateur result=null;
        try {
            mysql.getPstm().setString(1,login);
            mysql.getPstm().setString(2,password);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                result=new Utilisateur();
                result.setId(rs.getInt("id"));
                result.setNom(rs.getString("nom"));
                result.setPrenom(rs.getString("prenom"));
                result.setLogin(rs.getString("login"));
                result.setPassword(rs.getString("password"));
                result.setEtat(rs.getString("etat"));
                int profil_id = rs.getInt("profil_id");
                result.setProfil(pdao.findById(profil_id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }

    @Override
    public int create(Utilisateur objet) {
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getAgence().getId());
            mysql.getPstm().setInt(2,objet.getProfil().getId());
            mysql.getPstm().setString(3,objet.getNom());
            mysql.getPstm().setString(4,objet.getPrenom());
            mysql.getPstm().setString(5,objet.getLogin());
            mysql.getPstm().setString(6,encryptPassword(objet.getPassword()));
            mysql.getPstm().setString(7,objet.getEtat());
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public int update(Utilisateur objet) {
        mysql.initPS(SQL_UPDATE);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getProfil().getId());
            mysql.getPstm().setString(2,objet.getNom());
            mysql.getPstm().setString(3,objet.getPrenom());
            mysql.getPstm().setString(4,objet.getEtat());
            mysql.getPstm().setInt(5,objet.getId());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public List<Utilisateur> findAll() {
        mysql.initPS(SQL_FINDALL);
        List<Utilisateur> result=null;
        try {
              ResultSet rs=mysql.executeSelect();
              result=new ArrayList<>();
              while(rs.next()){
                Utilisateur user=new Utilisateur();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setEtat(rs.getString("etat"));
                int profil_id = rs.getInt("profil_id");
                user.setProfil(pdao.findById(profil_id));
                result.add(user);
              }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }

    @Override
    public Utilisateur findById(int id) {
        mysql.initPS(SQL_FIND);
        Utilisateur result=null;
        try {
            mysql.getPstm().setInt(1,id);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                result=new Utilisateur();
                result.setId(rs.getInt("id"));
                result.setNom(rs.getString("nom"));
                result.setPrenom(rs.getString("prenom"));
                result.setLogin(rs.getString("login"));
                result.setPassword(rs.getString("password"));
                result.setEtat(rs.getString("etat"));
                int profil_id = rs.getInt("profil_id");
                result.setProfil(pdao.findById(profil_id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }
     //CRYPTER MOT DE PASSE
    private String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(password.getBytes("UTF-8"));
        return new BigInteger(1, crypt.digest()).toString(16);
    }
}
