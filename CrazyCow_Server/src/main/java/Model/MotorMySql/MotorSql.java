package Model.MotorMySql;

import java.sql.*;

/**
 * Implementación concreta de IMotorSql para MySQL.
 * Gestiona la conexión y ejecución de operaciones con la base de datos MySQL.
 */

public class MotorSql implements IMotorSql{


    // ==================== CONSTANTES DE CONEXIÓN ====================

    /** Driver JDBC para MySQL */
    private static  final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    /** URL de conexión a la base de datos */
    private  static final String MYSQL_URL = "jdbc:mysql://db.cnfp5sduwtx2.us-east-1.rds.amazonaws.com:3306/CCdb";

    /** Usuario para autenticación */
    private static final String MYSQL_USER ="HR";

    /** Contraseña para autenticación */
    private static final String MYSQL_PASS = "Cambiame2025";


    // ==================== ATRIBUTOS DE CONEXIÓN ====================

    /** Objeto Connection para la conexión con la BD */
    private Connection m_Connection;

    /** Objeto Statement para ejecutar consultas estáticas */
    private Statement m_Statement;

    /** Objeto PreparedStatement para consultas parametrizadas */
    private PreparedStatement m_PrepareStatement;

    /** Objeto ResultSet para almacenar resultados de consultas */
    private ResultSet m_ResultSet;



    // ==================== MÉTODOS DE CONEXIÓN ====================

    /**
     * Establece conexión con la base de datos MySQL.
     * Carga el driver JDBC y crea los objetos Statement necesarios.
     *
     * @throws RuntimeException Si no puede cargar el driver JDBC
     */

    @Override
    public void connect() {
        try{
            Class.forName(DRIVER_NAME);

            m_Connection= DriverManager.getConnection(MYSQL_URL,MYSQL_USER,MYSQL_PASS);
            m_Statement = m_Connection.createStatement();
        }

        catch (ClassNotFoundException e){
            throw new RuntimeException();
        }
        catch (SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
        }
    }


    // ==================== MÉTODOS DE EJECUCIÓN ====================


    /**
     * Ejecuta un PreparedStatement previamente configurado.
     *
     * @param stmt PreparedStatement a ejecutar
     * @return true si la ejecución devuelve un ResultSet, false si es un conteo de filas
     */
    @Override
    public boolean execute(PreparedStatement stmt)
    {
        setPrepareStatement(stmt);
        return execute();
    }


    /**
     * Ejecuta el PreparedStatement almacenado.
     *
     * @return true si la ejecución devuelve un ResultSet, false si es un conteo de filas
     */
    @Override
    public boolean execute() {

        boolean bret= false;
        if (m_PrepareStatement != null)
        {
            try{
                bret= m_PrepareStatement.execute();
            }
            catch (SQLException sqlEx)
            {
                System.out.println(sqlEx.getMessage());
            }

        }
        return bret;
    }


    /**
     * Ejecuta una consulta SQL de tipo SELECT.
     *
     * @param sql Consulta SQL a ejecutar
     * @return ResultSet con los resultados o null si hay error
     */
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


    /**
     * Ejecuta una operación de actualización (INSERT, UPDATE, DELETE).
     *
     * @param stmt PreparedStatement con la operación
     * @return Número de filas afectadas
     */
    @Override
    public int executeUpdate(PreparedStatement stmt) {
        int filasAfectadas =0;
        try
        {
            filasAfectadas =stmt.executeUpdate();
        }
        catch (SQLException sqlEx){
            System.out.println("Error en executeUpdate:" + sqlEx.getMessage());
        }
        return filasAfectadas;
    }

    // ==================== MÉTODOS DE GESTIÓN ====================

    /**
     * Cierra todos los recursos de conexión de forma segura.
     * Verifica que cada recurso no sea nulo y esté abierto antes de cerrarlo.
     */

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

    // ==================== GETTERS/SETTERS ====================

    /**
     * Obtiene la conexión activa.
     *
     * @return Objeto Connection actual
     */

    public  Connection getConnection(){

        return m_Connection;
    }

    /**
     * Establece el PreparedStatement para ejecuciones posteriores.
     *
     * @param st PreparedStatement a almacenar
     */

    //funciona como un setter
    @Override
    public void setPrepareStatement(PreparedStatement st) {
        m_PrepareStatement = st;
    }


}
