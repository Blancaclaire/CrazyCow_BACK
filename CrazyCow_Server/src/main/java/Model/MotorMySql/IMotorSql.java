package Model.MotorMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface IMotorSql {
    public void connect();
    public boolean execute(PreparedStatement stmt);
    public boolean execute();
    //public boolean execute(String sql);
    public ResultSet executeQuery(String sql);
    public void disconnect();
    
    public Connection getConnection();
    public  void  setPrepareStatement(PreparedStatement st);

    public int executeUpdate(PreparedStatement stmt);
}
