package Model.DAO;

import Model.Entities.Customer;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDao {

    private final String SQL_LOGIN = "SELECT * FROM CUSTOMERS WHERE user_name = ? and password = ? ";
    private final String SQL_ADD_CUSTOMER = "INSERT INTO CUSTOMERS (name, surname, email, phone_number, user_name, password, address) VALUES (?,?,?,?,?,?,?)";

    private IMotorSql motorSql;

    //Constructor
    public CustomerDao(){
        motorSql= new MotorSql();
    }


    public int add(Object bean) {
        int filas =0;

        if (bean !=null && bean instanceof Customer){
            Customer customer = (Customer) bean;
            PreparedStatement ps = null;
            String sql = SQL_ADD_CUSTOMER;

            try{
                motorSql.connect();

                ps = motorSql.getConnection().prepareStatement(sql);


                ps.setString(1, customer.getName());
                ps.setString(2, customer.getSurname());
                ps.setString(3, customer.getEmail());
                ps.setString(4,customer.getPhone_number());
                ps.setString(5, customer.getUser_name());
                ps.setString(6, customer.getPassword());
                ps.setString(7, customer.getAddress());

                filas =motorSql.executeUpdate(ps);

            }
            catch (SQLException sqlException){
                System.out.println(sqlException.getMessage());
            }
            finally {
                motorSql.disconnect();
            }

        }
        else{
            System.out.println("Error: El objeto recibido no es un producto válido o es nulo.");
        }
        return filas;
    }

    public ArrayList findAll(Object bean) {
        return null;
    }
}
