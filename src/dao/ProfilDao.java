package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
public class ProfilDao implements IDao<Profil> {
    private final String SQL_INSERT  = "INSERT INTO profil VALUES(null,?,?)";
    private final String SQL_FINDALL = "SELECT * FROM profil";
    private final String SQL_UPDATE  = "UPDATE profil SET libelle=?,description=? WHERE id=?";
    private final String SQL_FIND    = "SELECT * FROM profil WHERE id=?";
    private ISGBD mysql;
    public ProfilDao(ISGBD mysql){
        this.mysql = mysql; 
    }

    @Override
    public int create(Profil objet) {
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setString(1,objet.getLibelle());
            mysql.getPstm().setString(2,objet.getDescription());
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ProfilDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public int update(Profil objet) {
        mysql.initPS(SQL_UPDATE);
        int id=0;
        try {
            mysql.getPstm().setString(1,objet.getLibelle());
            mysql.getPstm().setInt(2,objet.getId());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(ProfilDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public List<Profil> findAll() {
        mysql.initPS(SQL_FINDALL);
        List<Profil> result=null;
        try {
              ResultSet rs=mysql.executeSelect();
              result=new ArrayList<>();
              while(rs.next()){
                  Profil p=new Profil();
                  p.setId(rs.getInt("id"));
                  p.setLibelle(rs.getString("libelle"));
                  p.setDescription(rs.getString("description"));
                  result.add(p);
              }
        } catch (SQLException ex) {
            Logger.getLogger(ProfilDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }

    @Override
    public Profil findById(int id) {
        mysql.initPS(SQL_FIND);
        Profil result=null;
        try {
            mysql.getPstm().setInt(1,id);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                result=new Profil();
                result.setId(rs.getInt("id"));
                result.setLibelle(rs.getString("libelle"));
                result.setDescription(rs.getString("description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfilDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }
}
