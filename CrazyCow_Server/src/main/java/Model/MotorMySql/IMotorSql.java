package Model.MotorMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Interfaz que define el contrato para los motores de base de datos SQL.
 * Establece las operaciones básicas de conexión, ejecución y gestión
 * de transacciones con bases de datos relacionales.
 */
public interface IMotorSql {

    /**
     * Establece una conexión con la base de datos.
     * Debe inicializar todos los recursos necesarios para las operaciones posteriores.
     */
    public void connect();

    /**
     * Ejecuta un PreparedStatement previamente configurado.
     * @param stmt PreparedStatement con la consulta parametrizada
     * @return true si el resultado es un ResultSet, false si es un conteo de filas
     */
    public boolean execute(PreparedStatement stmt);

    /**
     * Ejecuta el PreparedStatement almacenado internamente.
     * @return true si el resultado es un ResultSet, false si es un conteo de filas
     */
    public boolean execute();

    /**
     * Ejecuta una consulta SQL de tipo SELECT.
     * @param sql Consulta SQL a ejecutar
     * @return ResultSet con los resultados de la consulta
     */
    public ResultSet executeQuery(String sql);

    /**
     * Cierra todos los recursos de conexión de forma segura.
     * Debe liberar Connection, Statement, PreparedStatement y ResultSet.
     */
    public void disconnect();

    /**
     * Obtiene la conexión activa con la base de datos.
     * @return Objeto Connection actual
     */
    public Connection getConnection();

    /**
     * Configura el PreparedStatement para ejecuciones posteriores.
     * @param st PreparedStatement a almacenar
     */
    public  void  setPrepareStatement(PreparedStatement st);

    /**
     * Ejecuta una operación de actualización (INSERT/UPDATE/DELETE).
     * @param stmt PreparedStatement con la operación
     * @return Número de filas afectadas por la operación
     */
    public int executeUpdate(PreparedStatement stmt);
}
