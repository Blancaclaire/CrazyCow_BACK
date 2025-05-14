package Model.DAO;

import Model.Entities.Order;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDao implements IDao{

    private final String SQL_FIND_ALL = "SELECT * FROM ORDERS WHERE 1=1";
    private final String SQL_INSERT_ORDER = "INSERT INTO ORDERS (customer_id, restaurant_id,order_date, order_status, total, location) VALUES (?,?, TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS'),?,?,?) ";
    private IMotorSql motorSql;


    public OrderDao(){
        motorSql = new MotorSql();
    }

    @Override
    public int add(Object bean) {
        return 0;
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

        try{
            motorSql.connect();

            ps =motorSql.getConnection().prepareStatement(SQL_FIND_ALL);

            rs = ps.executeQuery();

            while (rs.next()){
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
        }
        finally {
            motorSql.disconnect();
        }

        return listOrders;
    }
}
