package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
public class ClientDao implements IDao<Client> {
    private final String SQL_INSERT  = "INSERT INTO client VALUES(null,?,?,?,?,?)";
    private final String SQL_FINDALL = "SELECT * FROM client";
    private final String SQL_UPDATE  = "UPDATE client SET user_id=?,adresse=?,cni=?,telephone=?,email=? WHERE idClient=?";
    private final String SQL_FIND    = "SELECT * FROM client WHERE idClient=?";
    private final String SQL_FIND_CLIENT_USER    = "SELECT * FROM client WHERE user_id=?";
    private ISGBD mysql;
    private UtilisateurDao udao;
    public ClientDao(ISGBD mysql){
        this.mysql = mysql;
        udao = new UtilisateurDao(mysql);
    }

    @Override
    public int create(Client objet) {
        int user_id =udao.create(objet.getUser());
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setInt(1,user_id);
            mysql.getPstm().setString(2,objet.getAdresse());
            mysql.getPstm().setString(3,objet.getCni());
            mysql.getPstm().setString(4,objet.getTelephone());
            mysql.getPstm().setString(5,objet.getEmail());
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }
   

    @Override
    public int update(Client objet) {
        mysql.initPS(SQL_UPDATE);
        int user_id =udao.update(objet);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getUser().getId());
            mysql.getPstm().setString(2,objet.getAdresse());
            mysql.getPstm().setString(3,objet.getCni());
            mysql.getPstm().setString(4,objet.getTelephone());
            mysql.getPstm().setString(5,objet.getEmail());
            mysql.getPstm().setInt(6,objet.getIdClient());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public List<Client> findAll() {
        mysql.initPS(SQL_FINDALL);
        List<Client> result=null;
        try {
              ResultSet rs=mysql.executeSelect();
              result=new ArrayList<>();
              while(rs.next()){
                Client client=new Client();
                client.setId(rs.getInt("idClient"));
                client.setAdresse(rs.getString("adresse"));
                client.setCni(rs.getString("cni"));
                client.setTelephone(rs.getString("telephone"));
                client.setEmail(rs.getString("email"));
                int user_id = rs.getInt("user_id");
                client.setUser(udao.findById(user_id));
                result.add(client);
              }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return result;
    }

    @Override
    public Client findById(int idClient) {
        mysql.initPS(SQL_FIND);
        Client client=new Client();
        try {
            mysql.getPstm().setInt(1,idClient);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                int user_id = rs.getInt("user_id");
                client = new Client(idClient, rs.getString("adresse"), rs.getString("cni"), rs.getString("telephone")
                        , rs.getString("email"), udao.findById(user_id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return client;
    }
    public Client findByUser(int user_id) {
        mysql.initPS(SQL_FIND_CLIENT_USER);
        Client client=new Client();
        try {
            mysql.getPstm().setInt(1,user_id);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                client = new Client(rs.getInt("ClientId"), rs.getString("adresse"), rs.getString("cni"), rs.getString("telephone")
                        , rs.getString("email"), udao.findById(user_id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return client;
    }
}
