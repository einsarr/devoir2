package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
public class AgenceDao implements IDao<Agence> {
    private final String SQL_INSERT  = "INSERT INTO agence(libelle,telephone,email,adresse) VALUES(?,?,?,?)";
    private final String SQL_FINDALL = "SELECT * FROM agence";
    private final String SQL_UPDATE  = "UPDATE agence SET libelle=?,telephone=?,email=?"
            + ",adresse=? WHERE id=?";
    private final String SQL_FIND    = "SELECT * FROM agence WHERE id=?";
    private ISGBD mysql;
    public AgenceDao(ISGBD mysql){
        this.mysql = mysql; 
    }

    @Override
    public int create(Agence objet) {
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setString(1,objet.getLibelle());
            mysql.getPstm().setString(2,objet.getTelephone());
            mysql.getPstm().setString(3,objet.getEmail());
            mysql.getPstm().setString(4,objet.getAdresse());
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public int update(Agence objet) {
        mysql.initPS(SQL_UPDATE);
        int id=0;
        try {
            mysql.getPstm().setString(1,objet.getLibelle());
            mysql.getPstm().setString(2,objet.getTelephone());
            mysql.getPstm().setString(3,objet.getEmail());
            mysql.getPstm().setString(4,objet.getAdresse());
            mysql.getPstm().setInt(5,objet.getId());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public List<Agence> findAll() {
        mysql.initPS(SQL_FINDALL);
        List<Agence> result=null;
        try {
              ResultSet rs=mysql.executeSelect();
              result=new ArrayList<>();
              while(rs.next()){
                Agence p=new Agence();
                p.setId(rs.getInt("id"));
                p.setLibelle(rs.getString("libelle"));
                p.setTelephone(rs.getString("telephone"));
                p.setEmail(rs.getString("email"));
                p.setAdresse(rs.getString("adresse"));
                result.add(p);
              }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }

    @Override
    public Agence findById(int id) {
        mysql.initPS(SQL_FIND);
        Agence result=null;
        try {
            mysql.getPstm().setInt(1,id);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                result=new Agence();
                result.setId(rs.getInt("id"));
                result.setLibelle(rs.getString("libelle"));
                result.setTelephone(rs.getString("telephone"));
                result.setEmail(rs.getString("email"));
                result.setAdresse(rs.getString("adresse"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }
}
