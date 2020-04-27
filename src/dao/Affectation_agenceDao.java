package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
public class Affectation_agenceDao implements IDao<Affectation_agence> {
    private final String SQL_INSERT  = "INSERT INTO affectation_agence(agence_id,user_id) VALUES(?,?)";
    private final String SQL_FINDALL = "SELECT * FROM affectation_agence";
    private final String SQL_UPDATE  = "UPDATE affectation_agence SET agence_id=?,user_id=?,WHERE id=?";
    private final String SQL_FIND    = "SELECT * FROM affectation_agence WHERE id=?";
    private ISGBD mysql;
    private UtilisateurDao udao;
    private AgenceDao adao;
    public Affectation_agenceDao(ISGBD mysql){
        this.mysql = mysql;
        udao = new UtilisateurDao(mysql);
        adao = new AgenceDao(mysql);
    }

    @Override
    public int create(Affectation_agence objet) {
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getAgence().getId());
            mysql.getPstm().setInt(2,objet.getUser().getId());
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Affectation_agenceDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        mysql.CloseConnection();
        return id;
    }

    @Override
    public int update(Affectation_agence objet) {
        mysql.initPS(SQL_UPDATE);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getAgence().getId());
            mysql.getPstm().setInt(2,objet.getUser().getId());
            mysql.getPstm().setInt(3,objet.getId());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(Affectation_agenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public List<Affectation_agence> findAll() {
        mysql.initPS(SQL_FINDALL);
        List<Affectation_agence> affectations=null;
        try {
              ResultSet rs=mysql.executeSelect();
              affectations=new ArrayList<>();
              while(rs.next()){
                Affectation_agence aff=new Affectation_agence();
                aff.setId(rs.getInt("id"));
                int agence_id = rs.getInt("agence_id");
                aff.setAgence(adao.findById(agence_id));
                int user_id = rs.getInt("user_id");
                aff.setUser(udao.findById(user_id));
                Date date1 = rs.getDate("dateDebut");
                Date date2 = rs.getDate("dateFin");
                aff.setDateDebut(date1);
                aff.setDateFin(date2);
                affectations.add(aff);
              }
        } catch (SQLException ex) {
            Logger.getLogger(Affectation_agenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return affectations;
    }

    @Override
    public Affectation_agence findById(int id) {
        mysql.initPS(SQL_FIND);
        Affectation_agence aff=null;
        try {
            mysql.getPstm().setInt(1,id);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                aff=new Affectation_agence();
                aff.setId(rs.getInt("id"));
                int agence_id = rs.getInt("agence_id");
                aff.setAgence(adao.findById(agence_id));
                int user_id = rs.getInt("user_id");
                aff.setUser(udao.findById(user_id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Affectation_agenceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return aff;
    }
}
