package Model.MotorMySql;

import java.sql.*;

public class MotorSql implements IMotorSql{

    private static  final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private  static final String MYSQL_URL = "jdbc:mysql://db.cnfp5sduwtx2.us-east-1.rds.amazonaws.com:3306/CCdb";
    private static final String MYSQL_USER ="HR";
    private static final String MYSQL_PASS = "Cambiame2025";

    private Connection m_Connection; //Se usa para hacer la conexion con la base de datos
    private Statement m_Statement; //Se usa para ejecutar sentencias en la base de datos desde el servidor
    private PreparedStatement m_PrepareStatement; //Se usa para ejecutar sentencias con parametros
    private ResultSet m_ResultSet; //Almacena los datos resultao de la ejecucion de la sentencia en la bbdd

    //PreparedStatement evita las inyecciones de SQL porque los parámetros se envian por separado y no se conectan directamente dentrod e la consulat


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

    //Guarda el prepareStatement y llama a execute
    @Override
    public boolean execute(PreparedStatement stmt)
    {
        setPrepareStatement(stmt);
        return execute();
    }

//Si el prepared Statement esta definido lo ejecuta
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

    //la anterior al prepareStatement
    /*
    @Override
    public boolean execute(String sql) {
        boolean bRet = false;
        try{
            bRet = m_Statement.execute(sql);
        }
        catch (SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());

        }
        return bRet;
    }*/



    //Ejecuta la sentencia e tipo SELECT y obtiene los resultados de la base de datos
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

    @Override
    public int executeUpdate(PreparedStatement stmt) {
        int filasAfectadas =0;
        try
        {
            filasAfectadas =stmt.executeUpdate(); //metodo predefinido que incluye java cuando  creas un PrepareStatement
        }
        catch (SQLException sqlEx){
            System.out.println("Error en executeUpdate:" + sqlEx.getMessage());
        }
        return filasAfectadas;
    }

    public  Connection getConnection(){

        return m_Connection;
    }

    //funciona como un setter
    @Override
    public void setPrepareStatement(PreparedStatement st) {
        m_PrepareStatement = st;
    }


}
