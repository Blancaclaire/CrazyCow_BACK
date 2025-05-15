package Model.DAO;

import Model.Entities.Order;
import Model.Entities.OrderDetail;
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

    @Override
    public int add(Object bean) {
        int filas = 0;
        ResultSet rs = null;
        if (bean != null && bean instanceof Order) {
            Order order = (Order) bean;
            int orderId = -1;
            try {
                motorSql.connect();
                motorSql.getConnection().setAutoCommit(false); //Deshabilita autocommit
                PreparedStatement ps = motorSql.getConnection().prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);

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
                    orderDetail.setOrder_id(orderId);
                    OrderDetailDao orderDetailDao = new OrderDetailDao();  // Instancia creada
                    orderDetailDao.add(orderDetail);
                }

                //Insertar pago en PAYMENTS

                if (order.getPayment() != null) {
                    PaymentDao paymentDao = new PaymentDao(); // Creas instancia de PaymentDao
                    paymentDao.add(order.getPayment());      // Llamas a su metodo add()
                }

                // 5. Insertar Payment (corregido parámetro faltante)
                if (order.getPayment() != null) {
                    // Corrige la consulta SQL (holder_name estaba duplicado)
                    String SQL_INSERT_PAYMENT = "INSERT INTO PAYMENTS(order_id,holder_name,holder_number,cvv,card_type,price) VALUES (?,?,?,?,?,?)";

                    PreparedStatement psPayment = motorSql.getConnection().prepareStatement(SQL_INSERT_PAYMENT);
                    psPayment.setInt(1, orderId);
                    psPayment.setString(2, order.getPayment().getHolder_name());
                    psPayment.setString(3, order.getPayment().getHolder_number());
                    psPayment.setString(4, order.getPayment().getCvv());
                    psPayment.setString(5, order.getPayment().getCard_type());
                    psPayment.setDouble(6, order.getPayment().getPrice()); // Parámetro faltante
                    psPayment.executeUpdate();
                    psPayment.close();
                }

                motorSql.getConnection().commit();//Confirma transaccion

            } catch (SQLException sqlException) {
                try {


                    motorSql.getConnection().rollback(); // Rollback en error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("Error al intentar hacer un nuevo pedido" + sqlException.getMessage());
            }
            finally {
                motorSql.disconnect();
            }
        } else {
            System.out.println("Error: El objeto recibido no es un producto válido o es nulo.");
        }

        return filas;
    }
/*
    @Override
    public int add(Object bean) {
        int filas = 0;
        ResultSet rs = null;
        PreparedStatement psOrder = null;

        if (bean != null && bean instanceof Order) {
            Order order = (Order) bean;
            int orderId = -1;

            try {
                // 1. Conectar y configurar transacción
                motorSql.connect();
                motorSql.getConnection().setAutoCommit(false); // Deshabilitar autocommit

                // 2. Insertar Order principal
                psOrder = motorSql.getConnection().prepareStatement(
                        SQL_INSERT_ORDER,
                        Statement.RETURN_GENERATED_KEYS);

                psOrder.setInt(1, order.getCustomer_id());
                psOrder.setInt(2, order.getRestaurant_id());
                psOrder.setString(3, order.getOrder_status());
                psOrder.setDouble(4, order.getTotal());
                psOrder.setString(5, order.getLocation());

                filas = psOrder.executeUpdate();

                // 3. Obtener order_id generado
                rs = psOrder.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }

                // 4. Insertar OrderDetails
                if (order.getOrder_details() != null && !order.getOrder_details().isEmpty()) {
                    OrderDetailDao orderDetailDao = new OrderDetailDao();

                    for (OrderDetail orderDetail : order.getOrder_details()) {
                        orderDetail.setOrder_id(orderId);
                        orderDetailDao.add(orderDetail);
                    }
                }

                // 5. Insertar Payment (corregido parámetro faltante)
                if (order.getPayment() != null) {
                    // Corrige la consulta SQL (holder_name estaba duplicado)
                    String SQL_INSERT_PAYMENT = "INSERT INTO PAYMENTS(order_id,holder_name,holder_number,cvv,card_type,price) VALUES (?,?,?,?,?,?)";

                    PreparedStatement psPayment = motorSql.getConnection().prepareStatement(SQL_INSERT_PAYMENT);
                    psPayment.setInt(1, orderId);
                    psPayment.setString(2, order.getPayment().getHolder_name());
                    psPayment.setString(3, order.getPayment().getHolder_number());
                    psPayment.setString(4, order.getPayment().getCvv());
                    psPayment.setString(5, order.getPayment().getCard_type());
                    psPayment.setDouble(6, order.getPayment().getPrice()); // Parámetro faltante
                    psPayment.executeUpdate();
                    psPayment.close();
                }

                // 6. Confirmar transacción
                motorSql.getConnection().commit();

            } catch (SQLException sqlException) {
                try {
                    // Rollback en caso de error
                    if (motorSql.getConnection() != null) {
                        motorSql.getConnection().rollback();
                    }
                } catch (SQLException ex) {
                    System.err.println("Error al hacer rollback: " + ex.getMessage());
                }
                System.err.println("Error en transacción: " + sqlException.getMessage());
                filas = 0; // Indicar que falló

            } finally {
                // 7. Limpieza de recursos
                try {
                    if (rs != null) rs.close();
                    if (psOrder != null) psOrder.close();
                    if (motorSql.getConnection() != null) {
                        motorSql.getConnection().setAutoCommit(true); // Restaurar autocommit
                    }
                } catch (SQLException e) {
                    System.err.println("Error al liberar recursos: " + e.getMessage());
                }
                motorSql.disconnect();
            }
        } else {
            System.err.println("Error: El objeto recibido no es un pedido válido o es nulo.");
        }

        return filas;
    }*/

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
        ResultSet rsOrder = null;
        ResultSet rsDetailS = null;
        PreparedStatement psOrders = null;
        PreparedStatement psDetails = null;

        try {
            motorSql.connect();

            psOrders = motorSql.getConnection().prepareStatement(SQL_FIND_ALL);

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
                rsDetailS = psDetails.executeQuery();

                ArrayList<OrderDetail> details = new ArrayList<>();
                while (rsDetailS.next()) {
                    OrderDetail detail = new OrderDetail();
                    detail.setOrder_id(rsDetailS.getInt("order_id"));
                    detail.setProduct_id(rsDetailS.getInt("product_id"));
                    detail.setQuantity(rsDetailS.getInt("quantity"));
                    details.add(detail);
                }

                order.setOrder_details(details);

                listOrders.add(order);

                rsDetailS.close();

            }
        } catch (SQLException e) {
            System.out.println("ERROR en findAll OrdersDao" + e.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return listOrders;
    }
}
