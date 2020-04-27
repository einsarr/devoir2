package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
public class GuichetDao implements IDao<Guichet> {
    private final String SQL_INSERT  = "INSERT INTO guichet VALUES(null,?,?,?)";
    private final String SQL_FINDALL = "SELECT * FROM guichet";
    private final String SQL_UPDATE  = "UPDATE guichet SET agence_id=?,numero=?,description=? WHERE id=?";
    private final String SQL_FIND    = "SELECT * FROM guichet WHERE id=?";
    private ISGBD mysql;
    private AgenceDao adao;
    public GuichetDao(ISGBD mysql){
        this.mysql = mysql;
        adao = new AgenceDao(mysql);
    }

    @Override
    public int create(Guichet objet) {
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getAgence().getId());
            mysql.getPstm().setInt(2,objet.getNumero());
            mysql.getPstm().setString(3,objet.getDescription());
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(GuichetDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public int update(Guichet objet) {
        mysql.initPS(SQL_UPDATE);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getAgence().getId());
            mysql.getPstm().setInt(2,objet.getNumero());
            mysql.getPstm().setString(3,objet.getDescription());
            mysql.getPstm().setInt(4,objet.getId());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(GuichetDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public List<Guichet> findAll() {
        mysql.initPS(SQL_FINDALL);
        List<Guichet> result=null;
        try {
              ResultSet rs=mysql.executeSelect();
              result=new ArrayList<>();
              while(rs.next()){
                Guichet guichet=new Guichet();
                guichet.setId(rs.getInt("id"));
                guichet.setNumero(rs.getInt("numero"));
                guichet.setDescription(rs.getString("description"));
                int agence_id = rs.getInt("agence_id");
                guichet.setAgence(adao.findById(agence_id));
                result.add(guichet);
              }
        } catch (SQLException ex) {
            Logger.getLogger(GuichetDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }

    @Override
    public Guichet findById(int id) {
        mysql.initPS(SQL_FIND);
        Guichet result=null;
        try {
            mysql.getPstm().setInt(1,id);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                result=new Guichet();
                result.setId(rs.getInt("id"));
                result.setNumero(rs.getInt("numero"));
                result.setDescription(rs.getString("description"));
                int agence_id = rs.getInt("agence_id");
                result.setAgence(adao.findById(agence_id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GuichetDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }
}
