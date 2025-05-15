package Model.DAO;

import Model.Entities.Order;
import Model.Entities.OrderDetail;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDao implements IDao {

    private final String SQL_FIND_ALL = "SELECT * FROM ORDERS WHERE 1=1";
    private final String SQL_INSERT_ORDER = "INSERT INTO ORDERS (customer_id, restaurant_id,order_date, order_status, total, location) VALUES (?,?, CURRENT_TIMESTAMP,?,?,?) ";
    private final String SQL_INSERT_PRODUCT_ORDER = "INSERT INTO PRODUCTS_ORDER (order_id, product_id, quantity) VALUES (?,?,?)";

    private IMotorSql motorSql;


    public OrderDao() {
        motorSql = new MotorSql();
    }


    //Acordarse de esto
    //Cuando añades un pedido debe añadirse entonces tambien un detalle del pedido para que pueda motrarse posteriormente
    @Override
    public int add(Object bean) {
        int filas = 0;
        ResultSet rs = null;
        if (bean != null && bean instanceof Order) {
            Order order = (Order) bean;
            int orderId = -1;
            try {
                motorSql.connect();

                PreparedStatement ps = motorSql.getConnection().prepareStatement(SQL_INSERT_ORDER);

                ps.setInt(1, order.getCustomer_id());
                ps.setInt(2, order.getRestaurant_id());
                ps.setString(3, order.getOrder_status());
                ps.setDouble(4, order.getTotal());
                ps.setString(5, order.getLocation());

                filas = motorSql.executeUpdate(ps);

                // Obtener el order_id generado
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }

                //Insertar detalles del pedido en PRODUCTS_ORDER
                for (OrderDetail orderDetail : order.getOrder_details()) {
                    ps = motorSql.getConnection().prepareStatement(SQL_INSERT_PRODUCT_ORDER);
                    ps.setInt(1, orderId);
                    ps.setInt(2, orderDetail.getOrder_id());
                    ps.setInt(3, orderDetail.getProduct_id());
                    ps.executeUpdate();
                }

                //Insertar pago en PAYMENTS
                /*
                pstmt = connection.prepareStatement(SQL_INSERT_PAYMENT);
                pstmt.setInt(1, orderId); // order_id como FK
                pstmt.setString(2, payment.getHolderName());
                pstmt.setString(3, payment.getHolderNumber());
                pstmt.setString(4, payment.getCvv());
                pstmt.setString(5, payment.getCardType());
                pstmt.setDouble(6, payment.getPrice());
                pstmt.executeUpdate();

                connection.commit();
*/

            } catch (SQLException sqlException) {
                System.out.println("Error al intentar hacer un nuevo pedido" + sqlException.getMessage());
            }
        } else {
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
        ArrayList<Order> listOrders = new ArrayList<Order>();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            motorSql.connect();

            ps = motorSql.getConnection().prepareStatement(SQL_FIND_ALL);

            rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("order_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("restaurant_id"),
                        rs.getDate("order_date"),
                        rs.getString("order_hour"),
                        rs.getString("order_status"),
                        rs.getDouble("total"),
                        rs.getString("location")
                );

                listOrders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("ERROR en findAll OrdersDao" + e.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return listOrders;
    }
}
