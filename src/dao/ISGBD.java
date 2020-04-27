package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface ISGBD {
    public  void getConnection();
    public void initPS(String sql);
    public int executeMaj();
    public ResultSet executeSelect();
    public PreparedStatement getPstm();
    public void CloseConnection();
}
