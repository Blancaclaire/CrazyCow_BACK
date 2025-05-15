package Model.DAO;

import Model.Entities.Payment;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDao implements IDao {

    private String SQL_INSERT_PAYMENT = "INSERT INTO PAYMENTS(order_id,holder_name,holder_name,cvv,card_type,price)";

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

            } catch (SQLException sqlException) {
                System.out.println("Error al intentar añadir el pago" + sqlException.getMessage());
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

    @Override
    public ArrayList findAll(Object bean) {
        return null;
    }
}
