package Model.DAO;

import Model.Entities.Order;
import Model.Entities.OrderDetail;
import Model.Entities.Payment;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDao implements IDao {

    private final String SQL_FIND_ALL = "SELECT * FROM ORDERS WHERE 1=1";
    private final String SQL_INSERT_ORDER = "INSERT INTO ORDERS (customer_id, restaurant_id,order_date, order_status, total, location) VALUES (?,?, CURRENT_TIMESTAMP,?,?,?) ";
    private final String SQL_INSERT_PRODUCT_ORDER = "INSERT INTO PRODUCTS_ORDER (order_id, product_id, quantity) VALUES (?,?,?)";
    private String SQL_INSERT_PAYMENT = "INSERT INTO PAYMENTS(order_id,holder_name,holder_name,cvv,card_type,price) VALUES (?,?,?,?,?,?)";

    private IMotorSql motorSql;


    public OrderDao() {
        motorSql = new MotorSql();
    }


    public int add(Object bean) {
        int filas = 0;
        ResultSet rs = null;
        PreparedStatement psOrder = null;
        PreparedStatement psDetails = null;
        PreparedStatement psPayment = null;

        if (bean != null && bean instanceof Order) {
            Order order = (Order) bean;

            try {
                // 1. Conectar y desactivar autocommit
                motorSql.connect();
                motorSql.getConnection().setAutoCommit(false);

                // 2. Insertar la orden principal
                psOrder = motorSql.getConnection().prepareStatement(
                        SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);

                psOrder.setInt(1, order.getCustomer_id());
                psOrder.setInt(2, order.getRestaurant_id());
                psOrder.setString(3, order.getOrder_status());
                psOrder.setDouble(4, order.getTotal());
                psOrder.setString(5, order.getLocation());

                filas = psOrder.executeUpdate();

                // 3. Obtener el ID generado
                rs = psOrder.getGeneratedKeys();
                int orderId = -1;
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }

                // 4. Insertar los detalles directamente aquí (solución sencilla)
                if (orderId != -1 && order.getOrder_details() != null) {
                    psDetails = motorSql.getConnection().prepareStatement(
                            "INSERT INTO PRODUCTS_ORDER (order_id, product_id, quantity) VALUES (?,?,?)");

                    for (OrderDetail detail : order.getOrder_details()) {
                        psDetails.setInt(1, orderId);
                        psDetails.setInt(2, detail.getProduct_id());
                        psDetails.setInt(3, detail.getQuantity());
                        psDetails.addBatch(); // Agregar al batch para mejor performance
                    }

                    psDetails.executeBatch(); // Ejecutar todos los inserts juntos
                }

                // 5. Insertar el pago si existe
                if (order.getPayment() != null && orderId != -1) {
                    String sqlPayment = "INSERT INTO PAYMENTS (order_id, holder_name, holder_number, cvv, card_type, price) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";

                    psPayment = motorSql.getConnection().prepareStatement(sqlPayment, Statement.RETURN_GENERATED_KEYS);

                    Payment payment = order.getPayment();
                    payment.setOrder_id(orderId); // Asignar el order_id al pago

                    psPayment.setInt(1, payment.getOrder_id());
                    psPayment.setString(2, payment.getHolder_name());
                    psPayment.setString(3, payment.getHolder_number());
                    psPayment.setString(4, payment.getCvv());
                    psPayment.setString(5, payment.getCard_type());
                    psPayment.setDouble(6, payment.getPrice());

                    psPayment.executeUpdate();

                    // Obtener el payment_id generado (opcional)
                    ResultSet rsPayment = psPayment.getGeneratedKeys();
                    if (rsPayment.next()) {
                        payment.setPayment_id(rsPayment.getInt(1));
                    }
                    rsPayment.close();
                }


                motorSql.getConnection().commit();

            } catch (SQLException e) {
                try {
                    if (motorSql.getConnection() != null) {
                        motorSql.getConnection().rollback();
                    }
                } catch (SQLException ex) {
                    System.out.println("Error al hacer rollback: " + ex.getMessage());
                }
                System.out.println("Error al crear pedido: " + e.getMessage());
                filas = 0; // Indicar fallo
            } finally {
                // Cerrar recursos
                try {
                    if (rs != null) rs.close();
                } catch (SQLException e) {
                }
                try {
                    if (psOrder != null) psOrder.close();
                } catch (SQLException e) {
                }
                try {
                    if (psDetails != null) psDetails.close();
                } catch (SQLException e) {
                }
                try {
                    if (psPayment != null) psPayment.close();
                } catch (SQLException e) {
                }
                motorSql.disconnect();
            }
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



    public ArrayList findAll(Object bean) {
        ArrayList<Order> listOrders = new ArrayList<Order>();
        ResultSet rsOrder = null;
        ResultSet rsDetails = null;
        PreparedStatement psOrders = null;
        PreparedStatement psDetails = null;

        try {
            motorSql.connect();

            // Construir la consulta SQL dinámicamente
            StringBuilder sqlQuery = new StringBuilder(SQL_FIND_ALL);

            // Si el bean es una Order con restaurant_id, añadimos el filtro
            if (bean != null && bean instanceof Order) {
                Order filter = (Order) bean;
                if (filter.getRestaurant_id() > 0) {
                    sqlQuery.append(" AND restaurant_id = ?");
                }
            }

            psOrders = motorSql.getConnection().prepareStatement(sqlQuery.toString());

            // Establecer parámetros si hay filtro
            if (bean != null && bean instanceof Order) {
                Order filter = (Order) bean;
                if (filter.getRestaurant_id() > 0) {
                    psOrders.setInt(1, filter.getRestaurant_id());
                }
            }

            rsOrder = psOrders.executeQuery();
            String SQL_FIND_DETAILS = "SELECT * FROM PRODUCTS_ORDER WHERE order_id=?";
            psDetails = motorSql.getConnection().prepareStatement(SQL_FIND_DETAILS);

            while (rsOrder.next()) {
                Order order = new Order(
                        rsOrder.getInt("order_id"),
                        rsOrder.getInt("customer_id"),
                        rsOrder.getInt("restaurant_id"),
                        rsOrder.getDate("order_date"),
                        rsOrder.getString("order_status"),
                        rsOrder.getDouble("total"),
                        rsOrder.getString("location")
                );

                psDetails.setInt(1, order.getOrder_id());
                rsDetails = psDetails.executeQuery();

                ArrayList<OrderDetail> details = new ArrayList<>();
                while (rsDetails.next()) {
                    OrderDetail detail = new OrderDetail();
                    detail.setOrder_id(rsDetails.getInt("order_id"));
                    detail.setProduct_id(rsDetails.getInt("product_id"));
                    detail.setQuantity(rsDetails.getInt("quantity"));
                    details.add(detail);
                }

                order.setOrder_details(details);
                listOrders.add(order);
                rsDetails.close();
            }
        } catch (SQLException e) {
            System.out.println("ERROR en findAll OrdersDao: " + e.getMessage());
        } finally {
            try {
                if (rsDetails != null) rsDetails.close();
                if (psDetails != null) psDetails.close();
                if (rsOrder != null) rsOrder.close();
                if (psOrders != null) psOrders.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando recursos: " + e.getMessage());
            }
            motorSql.disconnect();
        }

        return listOrders;
    }

}
