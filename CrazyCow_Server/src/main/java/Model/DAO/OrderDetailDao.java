package Model.DAO;

import Model.Entities.OrderDetail;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDao implements IDao{


    private final String SQL_FIND_ALL ="SELECT * FROM PRODUCTS_ORDER where 1=1 ";
    private  final String   SQL_INSERT_PRODUCT_ORDER = "INSERT INTO PRODUCTS_ORDER (order_id, product_id, quantity) VALUES (?,?,?)";

    private IMotorSql motorSql;

    public OrderDetailDao(){
        motorSql= new MotorSql();
    }

    @Override
    public int add(Object bean) {
        int filas =0;

        if (bean!=null && bean instanceof OrderDetail){
            OrderDetail orderDetail = (OrderDetail) bean;
            PreparedStatement ps = null;
            String sql = SQL_INSERT_PRODUCT_ORDER;

            try{
                motorSql.connect();

                ps = motorSql.getConnection().prepareStatement(sql);

                ps.setInt(1,orderDetail.getOrder_id());
                ps.setInt(2,orderDetail.getProduct_id());
                ps.setInt(3,orderDetail.getQuantity());

                filas = motorSql.executeUpdate(ps);
            }
            catch (SQLException sqlException){
                System.out.println(sqlException.getMessage());
            }
            finally {
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
