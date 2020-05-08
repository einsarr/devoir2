package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.*;
public class TransactionDao implements IDao<Transaction> {
    private final String SQL_INSERT  = "INSERT INTO transaction(guichet_id,compte_id,montant,type) VALUES(?,?,?,?)";
    private final String SQL_FINDALL = "SELECT * FROM transaction";
    private final String SQL_UPDATE  = "UPDATE transaction SET agence_id=?,compte_id=?,montant=?,type=? WHERE id=?";
    private final String SQL_FIND    = "SELECT * FROM transaction WHERE id=?";
    private final String SQL_FIND_BY_CLIENT   = "SELECT * FROM transaction WHERE compte_id IN(SELECT id FROM compte WHERE client_id"
            + "IN (SELECT id from client)";
    private ISGBD mysql;
    private CompteDao cdao;
    private GuichetDao gdao;
    public TransactionDao(ISGBD mysql){
        this.mysql = mysql;
        cdao = new CompteDao(mysql);
        gdao = new GuichetDao(mysql);
    }
    public List<Transaction> mesOperations(Client client)
    {
        mysql.initPS(SQL_FIND_BY_CLIENT);
        List<Transaction> transactions = new ArrayList<>();
        try {
            ResultSet rs=mysql.executeSelect();
            while(rs.next()){
                Transaction t=new Transaction();
                t.setId(rs.getInt("id"));
                t.setMontant(rs.getInt("montant"));
                t.setType(rs.getString("type"));
                int compte_id = rs.getInt("compte_id");
                t.setCompte(cdao.findById(compte_id));
                transactions.add(t);
              }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return transactions;
    }
    @Override
    public int create(Transaction objet) {
        mysql.initPS(SQL_INSERT);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getGuichet().getId());
            mysql.getPstm().setInt(2,objet.getCompte().getId());
            mysql.getPstm().setInt(3,objet.getMontant());
            mysql.getPstm().setString(4,objet.getType());
            
            mysql.executeMaj(); 
            ResultSet rs=mysql.getPstm().getGeneratedKeys();
            if(rs.next()) id= rs.getInt(1);
            /*if(objet.getType().compareToIgnoreCase("Retrait")==0){
                id = cdao.debiterCompte(objet.getCompte(),objet.getMontant());
            }
            else if(objet.getType().compareToIgnoreCase("Depot")==0)
            {
                id = cdao.crediterCompte(objet.getCompte(),objet.getMontant());
            } */  
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        mysql.CloseConnection();
        return id;
    }
    public int virement(Transaction objet,Transaction objet1) {
        cdao.debiterCompte(objet.getCompte(),objet.getMontant());
        return cdao.crediterCompte(objet1.getCompte(),objet.getMontant());
    }
    public int faireTransaction(List<Transaction> ListTransactions)
    {
        int id=0;
        for(Transaction t:ListTransactions)
        {
            mysql.initPS(SQL_INSERT);
            try {
                mysql.getPstm().setInt(1,t.getGuichet().getId());
                mysql.getPstm().setInt(2,t.getCompte().getId());
                mysql.getPstm().setInt(3,t.getMontant());
                mysql.getPstm().setString(4,t.getType());
                id = mysql.executeMaj(); 
                /*ResultSet rs=mysql.getPstm().getGeneratedKeys();
                if(rs.next()) id= rs.getInt(1);
                if(objet.getType().compareToIgnoreCase("Retrait")==0){
                    id = cdao.debiterCompte(objet.getCompte(),objet.getMontant());
                }
                else if(objet.getType().compareToIgnoreCase("Depot")==0)
                {
                    id = cdao.crediterCompte(objet.getCompte(),objet.getMontant());
                } */  
            } catch (SQLException ex) {
                Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, null, ex);
            } 
            mysql.CloseConnection();
        }
        return id;
    }

    @Override
    public int update(Transaction objet) {
        mysql.initPS(SQL_UPDATE);
        int id=0;
        try {
            mysql.getPstm().setInt(1,objet.getGuichet().getId());
            mysql.getPstm().setInt(2,objet.getCompte().getId());
            mysql.getPstm().setInt(3,objet.getMontant());
            mysql.getPstm().setString(4,objet.getType());
            mysql.getPstm().setInt(5,objet.getId());
            id=mysql.executeMaj(); 
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return id;
    }

    @Override
    public List<Transaction> findAll() {
        mysql.initPS(SQL_FINDALL);
        List<Transaction> affectations=null;
        try {
              ResultSet rs=mysql.executeSelect();
              affectations=new ArrayList<>();
              while(rs.next()){
                Transaction aff=new Transaction();
                aff.setId(rs.getInt("id"));
                aff.setMontant(rs.getInt("montant"));
                aff.setType(rs.getString("type"));
                aff.setCreatedAt(rs.getDate("createdAt"));
                int guichet_id = rs.getInt("guichet_id");
                aff.setGuichet(gdao.findById(guichet_id));
                int compte_id = rs.getInt("compte_id");
                aff.setCompte(cdao.findById(compte_id));
                affectations.add(aff);
              }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return affectations;
    }

    @Override
    public Transaction findById(int id) {
        mysql.initPS(SQL_FIND);
        Transaction aff=null;
        try {
            mysql.getPstm().setInt(1,id);
            ResultSet rs=mysql.executeSelect();
            if(rs.first()){
                aff=new Transaction();
                aff.setId(rs.getInt("id"));
                aff.setMontant(rs.getInt("montant"));
                aff.setType(rs.getString("type"));
                int guichet_id = rs.getInt("guichet_id");
                aff.setGuichet(gdao.findById(guichet_id));
                int compte_id = rs.getInt("compte_id");
                aff.setCompte(cdao.findById(compte_id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        mysql.CloseConnection();
        return aff;
    }
    
}
