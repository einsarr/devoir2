package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
public class CompteEpargneDao implements IDao<CompteEpargne> {
    private final String SQL_INSERT  = "INSERT INTO compteepargne(compte_id,taux) VALUES(?,?)";
    private final String SQL_FINDALL = "SELECT * FROM compteEpargne";
    private final String SQL_UPDATE  = "UPDATE compteEpargne SET user_id=?,adresse=?,cni=?,telephone=?,email=? WHERE idCompteEpargne=?";
    private final String SQL_FIND    = "SELECT * FROM compteEpargne WHERE idCompteEpargne=?";
    private ISGBD mysql;
    private CompteDao cdao;
    public CompteEpargneDao(ISGBD mysql){
        this.mysql = mysql;
        cdao = new CompteDao(mysql);
    }

    @Override
    public int create(CompteEpargne objet) {
        int compte_id =cdao.create(objet.getCompte());
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setInt(1,compte_id);
            mysql.getPstm().setFloat(2,objet.getTaux());
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CompteEpargneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }
     public int ajouterCompteEpargne(CompteEpargne objet) {
        int compte_id =cdao.ajouterCompte(objet.getCompte());
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setInt(1,compte_id);
            mysql.getPstm().setFloat(2,objet.getTaux());
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(CompteEpargneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public int update(CompteEpargne objet) {
        mysql.initPS(SQL_UPDATE);
        int compte_id =cdao.update(objet);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getCompte().getId());
            mysql.getPstm().setFloat(2,objet.getTaux());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(CompteEpargneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public List<CompteEpargne> findAll() {
        mysql.initPS(SQL_FINDALL);
        List<CompteEpargne> result=null;
        try {
              ResultSet rs=mysql.executeSelect();
              result=new ArrayList<>();
              while(rs.next()){
                CompteEpargne compteEpargne=new CompteEpargne();
                compteEpargne.setId(rs.getInt("id"));
                compteEpargne.setTaux(rs.getFloat("taux"));
                int compte_id = rs.getInt("compte_id");
                compteEpargne.setCompte(cdao.findById(compte_id));
                result.add(compteEpargne);
              }
        } catch (SQLException ex) {
            Logger.getLogger(CompteEpargneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }

    @Override
    public CompteEpargne findById(int id) {
        mysql.initPS(SQL_FIND);
        CompteEpargne compteEpargne=new CompteEpargne();
        try {
            mysql.getPstm().setInt(1,id);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                int compte_id = rs.getInt("compte_id");
                compteEpargne = new CompteEpargne(id, rs.getFloat("taux"),cdao.findById(compte_id));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompteEpargneDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return compteEpargne;
    }
}
