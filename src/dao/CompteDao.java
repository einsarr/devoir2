package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
public class CompteDao implements IDao<Compte> {
    private final String SQL_INSERT  = "INSERT INTO compte(client_id,numero,etat) VALUES(?,?,?)";
    private final String SQL_FINDALL = "SELECT * FROM compte";
    private final String SQL_UPDATE  = "UPDATE compte SET client_id=?,solde=? WHERE id=?";
    private final String SQL_FIND   = "SELECT * FROM compte WHERE id=?";
    private final String SQL_DEBITER_COMPTE  = "UPDATE compte SET solde=solde - ? WHERE id=?";
    private final String SQL_CREDITER_COMPTE  = "UPDATE compte SET solde=solde + ? WHERE id=?";
    private final String SQL_BLOQUER_COMPTE  = "UPDATE compte SET etat=? WHERE id=?";
     private final String RECHERCHER_COMPTE    = "SELECT * FROM compte WHERE numero=?";
    private ISGBD mysql;
    private ClientDao cdao;
    public CompteDao(ISGBD mysql){
        this.mysql = mysql;
        cdao = new ClientDao(mysql);
    }

    @Override
    public int create(Compte objet) {
        int client_id =cdao.create(objet.getClient());
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setInt(1,client_id);
            mysql.getPstm().setString(2,generateAccountNumber(objet.getClient()));
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        mysql.CloseConnection();
        return id;
    }
    public int ajouterCompte(Compte objet) {
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getClient().getIdClient());
            mysql.getPstm().setString(2,generateAccountNumber(objet.getClient()));
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        mysql.CloseConnection();
        return id;
    }
    public int bloqueCompte(Compte objet) {
        mysql.initPS(SQL_BLOQUER_COMPTE);
        int id=0;
        try {
            mysql.getPstm().setString(1,objet.getEtat());
            mysql.getPstm().setInt(2,objet.getId());
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        mysql.CloseConnection();
        return id;
    }
    
    public int crediterCompte(Compte objet,int montant) {
        mysql.initPS(SQL_CREDITER_COMPTE);
        int id=0;
        try {
            mysql.getPstm().setInt(1,montant);
            mysql.getPstm().setInt(2,objet.getId());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public int debiterCompte(Compte objet,int montant) {
        mysql.initPS(SQL_DEBITER_COMPTE);
        int id=0;
        try {
            mysql.getPstm().setInt(1,montant);
            mysql.getPstm().setInt(2,objet.getId());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    public int virement(Compte objet1,Compte objet2,int montant) {
        debiterCompte(objet1, montant);
        return crediterCompte(objet2, montant);
    }
    @Override
    public int update(Compte objet) {
        mysql.initPS(SQL_UPDATE);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getClient().getId());
            mysql.getPstm().setFloat(2,objet.getSolde());
            mysql.getPstm().setInt(3,objet.getId());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public List<Compte> findAll() {
        mysql.initPS(SQL_FINDALL);
        List<Compte> result=null;
        try {
              ResultSet rs=mysql.executeSelect();
              result=new ArrayList<>();
              while(rs.next()){
                Compte compte=new Compte();
                compte.setId(rs.getInt("id"));
                compte.setSolde(rs.getFloat("solde"));
                compte.setEtat(rs.getString("etat"));
                compte.setNumero(rs.getString("numero"));
                int client_id = rs.getInt("client_id");
                compte.setClient(cdao.findById(client_id));
                result.add(compte);
              }
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }

    @Override
    public Compte findById(int id) {
        mysql.initPS(SQL_FIND);
        Compte result=null;
        try {
            mysql.getPstm().setInt(1,id);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                result=new Compte();
                result.setId(rs.getInt("id"));
                result.setNumero(rs.getString("numero"));
                result.setSolde(rs.getInt("solde"));
                int client_id = rs.getInt("client_id");
                result.setClient(cdao.findById(client_id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }
    public Compte findByNumero(String numero)
    {
        mysql.initPS(RECHERCHER_COMPTE);
        Compte result=null;
        try {
            mysql.getPstm().setString(1,numero);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                int client_id = rs.getInt("client_id");
                result=new Compte(rs.getInt("id"), numero
                        ,rs.getInt("solde"), cdao.findById(client_id), rs.getString("etat"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }
    public String generateAccountNumber(Client client)
    {
            String codeNomClient = ""+client.getUser().getNom()+"";
            String codePrenomClient = ""+client.getUser().getPrenom()+"";
            String codeCniClient = client.getCni();
           
            String deuxpremierCaractereP = codePrenomClient.substring(0,2);
            String premierCaractereN = codeNomClient.substring(0,1);
            String QuatreDernierChiffreCni = codeCniClient.substring(codeCniClient.length()-4,codeCniClient.length());
            Random rand = new Random(); 
            int nombreAleatoire = rand.nextInt(9);
            return deuxpremierCaractereP+""+premierCaractereN+""+QuatreDernierChiffreCni+""+nombreAleatoire;
    }
    
}
