package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DaoMysql extends Config implements ISGBD{
    private Connection connexion;
    private PreparedStatement pStatement;
    private int execOk;
    private ResultSet resultSet;

    @Override
    public void getConnection() {
	try{		
            Class.forName("com.mysql.jdbc.Driver");
            connexion=DriverManager.getConnection("jdbc:mysql://"+Config.DB_HOST+":"+Config.DB_PORT+"/"+Config.DB_NAME,
                                                            Config.DB_USER,Config.DB_PASSWORD);
	}catch(Exception e)
	{
            System.err.println("Erreur de chargement du pilote ou de la connexion à la db "+e.getMessage());
	}
    }

    @Override
    public void initPS(String sql) {
        getConnection();
        try{
            if(sql.toLowerCase().startsWith("insert"))
                {
                    pStatement=connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
                }
            else{
                pStatement=connexion.prepareStatement(sql);
            }
        }catch(Exception e)
        {
            System.err.println("Erreur sur la requête d'insertion/maj : "+e.getMessage());
        }
    }

    @Override
    public int executeMaj() {
        try {
            execOk = pStatement.executeUpdate();
            } catch (Exception e) {
                System.err.println("Erreur d'éxécution de la requête ex : "+e.getMessage());
            }
        return execOk;
    }

    @Override
    public ResultSet executeSelect() {
        try {
            resultSet=pStatement.executeQuery();
            } catch (Exception e) {
                System.out.println("Erreur d'exécution de la requête save/maj"+e.getMessage());
            }
            return resultSet;
    }

    @Override
    public PreparedStatement getPstm() {
        return  this.pStatement;
    }

    @Override
    public void CloseConnection() {
        try{
            if(connexion!=null){
                connexion.close();
            }
        }catch(Exception e){
            System.err.println("Error de fermeture de connexion : "+e.getMessage());
        }
    }
}
