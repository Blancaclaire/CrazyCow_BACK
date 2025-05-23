package Model.DAO;

import Model.Entities.OrderDetail;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase DAO (Data Access Object) que gestiona las operaciones de base de datos
 * relacionadas con los detalles de un pedido (OrderDetail).
 * Implementa la interfaz IDao para garantizar consistencia en los métodos CRUD.
 */
public class OrderDetailDao implements IDao{


    private final String SQL_FIND_ALL ="SELECT * FROM PRODUCTS_ORDER where 1=1 ";
    private  final String   SQL_INSERT_PRODUCT_ORDER = "INSERT INTO PRODUCTS_ORDER (order_id, product_id, quantity) VALUES (?,?,?)";

    private IMotorSql motorSql;

    /** Constructor: Inicializa el motor de base de datos. */
    public OrderDetailDao(){
        motorSql= new MotorSql();
    }


    /**
     * Método para agregar un nuevo detalle de pedido a la base de datos.
     *
     * @param bean Objeto de tipo OrderDetail que contiene los datos del detalle.
     * @return Número de filas afectadas (0 si falla, 1 si éxito).
     */
    @Override
    public int add(Object bean) {
        int filas =0;


        if (bean!=null && bean instanceof OrderDetail){
            OrderDetail orderDetail = (OrderDetail) bean;
            PreparedStatement ps = null;
            String sql = SQL_INSERT_PRODUCT_ORDER;

            try{
                motorSql.connect();
                motorSql.getConnection().setAutoCommit(false);
                ps = motorSql.getConnection().prepareStatement(sql);

                ps.setInt(1,orderDetail.getOrder_id());
                ps.setInt(2,orderDetail.getProduct_id());
                ps.setInt(3,orderDetail.getQuantity());

                filas = motorSql.executeUpdate(ps);
                motorSql.getConnection().commit();
            }
            catch (SQLException sqlException){
                try {
                    motorSql.getConnection().rollback(); // Rollback en error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Error al intentar insertar los detalles del pedido. " + sqlException.getMessage());
            }
            finally {
                if (ps != null) {
                    try { ps.close(); } catch (SQLException e) { /* log */ }
                }
                motorSql.disconnect();
            }
        }
        else {
            System.out.println("Error: El objeto recibido no es un producto válido o es nulo.");
        }

        return filas;
    }

    @Override
    public int delete(Object e) {
        return 0;
    }

    @Override
    public int update(Object bean) {
        return 0;
    }

    @Override
    public ArrayList findAll(Object bean) {
        return null;
    }
}
