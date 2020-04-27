package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
public class Affectation_guichetDao implements IDao<Affectation_guichet> {
    private final String SQL_INSERT  = "INSERT INTO affectation_guichet(agence_id,guichet_id,user_id) VALUES(?,?,?)";
    private final String SQL_FINDALL = "SELECT * FROM affectation_guichet";
    private final String SQL_UPDATE  = "UPDATE affectation_guichet SET agence_id=?,user_id=?,WHERE id=?";
    private final String SQL_FIND    = "SELECT * FROM affectation_guichet WHERE id=?";
    private ISGBD mysql;
    private UtilisateurDao udao;
    private GuichetDao gdao;
    private AgenceDao adao;
    public Affectation_guichetDao(ISGBD mysql){
        this.mysql = mysql;
        udao = new UtilisateurDao(mysql);
        gdao = new GuichetDao(mysql);
        adao = new AgenceDao(mysql);
    }

    @Override
    public int create(Affectation_guichet objet) {
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getAgence().getId());
            mysql.getPstm().setInt(2,objet.getGuichet().getId());
            mysql.getPstm().setInt(3,objet.getUser().getId());
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Affectation_guichetDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        mysql.CloseConnection();
        return id;
    }

    @Override
    public int update(Affectation_guichet objet) {
        mysql.initPS(SQL_UPDATE);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getGuichet().getId());
            mysql.getPstm().setInt(2,objet.getUser().getId());
            mysql.getPstm().setInt(3,objet.getId());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(Affectation_guichetDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public List<Affectation_guichet> findAll() {
        mysql.initPS(SQL_FINDALL);
        List<Affectation_guichet> affectations=null;
        try {
              ResultSet rs=mysql.executeSelect();
              affectations=new ArrayList<>();
              while(rs.next()){
                Affectation_guichet aff=new Affectation_guichet();
                aff.setId(rs.getInt("id"));
                int agence_id = rs.getInt("agence_id");
                aff.setAgence(adao.findById(agence_id));
                int guichet_id = rs.getInt("guichet_id");
                aff.setGuichet(gdao.findById(guichet_id));
                int user_id = rs.getInt("user_id");
                aff.setUser(udao.findById(user_id));
                Date date1 = rs.getDate("dateDebut");
                Date date2 = rs.getDate("dateFin");
                aff.setDateFin(date2);
                aff.setDateDebut(date1);
                affectations.add(aff);
              }
        } catch (SQLException ex) {
            Logger.getLogger(Affectation_guichetDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return affectations;
    }

    @Override
    public Affectation_guichet findById(int id) {
        mysql.initPS(SQL_FIND);
        Affectation_guichet aff=null;
        try {
            mysql.getPstm().setInt(1,id);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                aff=new Affectation_guichet();
                aff.setId(rs.getInt("id"));
                int guichet_id = rs.getInt("guichet_id");
                aff.setGuichet(gdao.findById(guichet_id));
                int user_id = rs.getInt("user_id");
                aff.setUser(udao.findById(user_id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Affectation_guichetDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return aff;
    }
}
