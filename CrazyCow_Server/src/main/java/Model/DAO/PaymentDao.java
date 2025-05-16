package Model.DAO;

import Model.Entities.Payment;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDao implements IDao {

    private String SQL_INSERT_PAYMENT = "INSERT INTO PAYMENTS(order_id,holder_name,holder_number,cvv,card_type,price) VALUES (?,?,?,?,?,?)";

    private IMotorSql motorSql;

    public PaymentDao() {
        motorSql = new MotorSql();
    }


    @Override
    public int add(Object bean) {
        int filas = 0;

        if (bean != null && bean instanceof Payment) {

            try {
                Payment payment = (Payment) bean;

                motorSql.connect();

                PreparedStatement ps = motorSql.getConnection().prepareStatement(SQL_INSERT_PAYMENT);
                ps.setInt(1,payment.getOrder_id());
                ps.setString(2,payment.getHolder_name());
                ps.setString(3,payment.getHolder_number());
                ps.setString(4,payment.getCvv());
                ps.setString(5,payment.getCard_type());
                ps.setDouble(6,payment.getPrice());

                filas = ps.executeUpdate();

            } catch (SQLException sqlException) {
                System.out.println("Error al intentar añadir el pago " + sqlException.getMessage());
            }
            finally {
                motorSql.disconnect();
            }
        }
        else{
            System.out.println("ERROR. El objeto recibido no es válido o es nulo");
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
