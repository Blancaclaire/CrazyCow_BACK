package MotorMySql;

import java.sql.*;

public class MotorSql implements IMotorSql{

    private static  final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private  static final String MYSQL_URL = "jdbc:mysql:// db.cnfp5sduwtx2.us-east-1.rds.amazonaws.com:3306/CrazyCowDB";
    private static final String MYSQL_USER ="HR";
    private static final String MYSQL_PASS = "Cambiame2025";

    private Connection m_Connection;
    private Statement m_Statement;
    private ResultSet m_ResultSet; //Permite recorrer los datos fila por fila


    //Conecta con la base de datos
    @Override
    public void connect() {
        try{
            Class.forName(DRIVER_NAME); //Carga el driver JDBC de mysql

            m_Connection= DriverManager.getConnection(MYSQL_URL,MYSQL_USER,MYSQL_PASS); //establece la conexion con la base de datos
            m_Statement = m_Connection.createStatement(); //Se crea un Statement para ejectar sentencias en la base de datos

        }
        //Se ejecuta cuando la clase(Class.forName() es incorrecto,
        //Cuando el archivo .class correspondiente no esta en classpath
        //Si el driver JDBC no está disponible cuandp se intenta cargar
        catch (ClassNotFoundException e){
            throw new RuntimeException();
        }
        //Se ejecuta si los datos de acceso son incorrectos o el servidor no esta disponible
        //Problemas con  la sintaxis sql o violaciones de restricciones
        //Si el driver no esta correctamente configurado o es incompatible
        catch (SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
        }
    }

    //Ejecuta la sentencia y obtiene los resultados de la base de datos
    @Override
    public ResultSet executeQuery(String sql) {
        try{
            m_ResultSet = m_Statement.executeQuery(sql);
        }
        catch (SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
            m_ResultSet = null;
        }

        return m_ResultSet;
    }

    //Cierra las conexiones con la base de datos
    //Comprueba que los objetos de conexion con la base de datos siguen abiertos para cerrarlos
    @Override
    public void disconnect() {
        try{
            if (m_ResultSet !=null && !m_ResultSet.isClosed()){
                m_ResultSet.close();
            } if (m_Connection !=null && !m_Connection.isClosed()) {
                m_Connection.close();}
            if (m_Statement !=null && !m_Statement.isClosed()){
                m_Statement.close();
            }
        }
        catch (SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
        }
    }
}
