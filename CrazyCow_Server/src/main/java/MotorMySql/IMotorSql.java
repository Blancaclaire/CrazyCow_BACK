package MotorMySql;

import java.sql.ResultSet;

public interface IMotorSql {
    public void connect();
    public ResultSet executeQuery(String sql);
    public void disconnect();
}
