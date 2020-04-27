package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
public class CompteCheckDao implements IDao<CompteCheck> {
    private final String SQL_INSERT  = "INSERT INTO comptecheck(compte_id,frais) VALUES(?,?)";
    private final String SQL_FINDALL = "SELECT * FROM compteCheck";
    private final String SQL_UPDATE  = "UPDATE compteCheck SET user_id=?,adresse=?,cni=?,telephone=?,email=? WHERE id_cpt_ch=?";
    private final String SQL_FIND    = "SELECT * FROM compteCheck WHERE id_cpt_ch=?";
    private ISGBD mysql;
    private CompteDao cdao;
    public CompteCheckDao(ISGBD mysql){
        this.mysql = mysql;
        cdao = new CompteDao(mysql);
    }

    @Override
    public int create(CompteCheck objet) {
        int compte_id =cdao.create(objet.getCompte());
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setInt(1,compte_id);
            mysql.getPstm().setInt(2,objet.getFrais());
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CompteCheckDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }
     public int ajouterCompteCheck(CompteCheck objet) {
        int compte_id =cdao.ajouterCompte(objet.getCompte());
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setInt(1,compte_id);
            mysql.getPstm().setInt(2,objet.getFrais());
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CompteCheckDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public int update(CompteCheck objet) {
        mysql.initPS(SQL_UPDATE);
        int compte_id =cdao.update(objet);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getCompte().getId());
            mysql.getPstm().setInt(2,objet.getFrais());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(CompteCheckDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public List<CompteCheck> findAll() {
        mysql.initPS(SQL_FINDALL);
        List<CompteCheck> result=null;
        try {
              ResultSet rs=mysql.executeSelect();
              result=new ArrayList<>();
              while(rs.next()){
                CompteCheck compteCheck=new CompteCheck();
                compteCheck.setId(rs.getInt("id"));
                compteCheck.setFrais(rs.getInt("frais"));
                int compte_id = rs.getInt("compte_id");
                compteCheck.setCompte(cdao.findById(compte_id));
                result.add(compteCheck);
              }
        } catch (SQLException ex) {
            Logger.getLogger(CompteCheckDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }

    @Override
    public CompteCheck findById(int id) {
        mysql.initPS(SQL_FIND);
        CompteCheck compteCheck=new CompteCheck();
        try {
            mysql.getPstm().setInt(1,id);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                int compte_id = rs.getInt("compte_id");
                compteCheck = new CompteCheck(id, rs.getInt("taux"),cdao.findById(compte_id));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompteCheckDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return compteCheck;
    }
}
