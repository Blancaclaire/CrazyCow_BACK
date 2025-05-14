package Model.DAO;

import Model.Entities.Customer;
import Model.Entities.Product;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDao implements IDao {

    private final String SQL_LOGIN = "SELECT * FROM CUSTOMERS WHERE email = ? and password = ? ";
    private final String SQL_ADD_CUSTOMER = "INSERT INTO CUSTOMERS (name, surname, email, phone_number, user_name, password, address) VALUES (?,?,?,?,?,?,?)";
    private final String SQL_FIND_ALL = "SELECT * FROM CUSTOMERS WHERE 1=1";
    private final String SQL_FIND_BY_ID = "SELECT * FROM CUSTOMERS WHERE customer_id = ?";

    private IMotorSql motorSql;

    //Constructor
    public CustomerDao() {
        motorSql = new MotorSql();
    }


    public boolean authenticate(Object bean) {
        boolean isUser = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (bean != null && bean instanceof Customer) {

                Customer customer = (Customer) bean;
                motorSql.connect();
                ps = motorSql.getConnection().prepareStatement(SQL_LOGIN);
                ps.setString(1, customer.getEmail());
                ps.setString(2, customer.getPassword());

                rs = ps.executeQuery();
                isUser = rs.next(); //Si hay resultados, las credenciales son validas
            }
        } catch (SQLException sqlException) {
            System.out.println("Error al intentar comprobar la autenticacion del usuario" + sqlException.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return isUser;
    }


    //metodos de la interfaz

    @Override
    public int add(Object bean) {
        int filas = 0;

        if (bean != null && bean instanceof Customer) {
            Customer customer = (Customer) bean;
            PreparedStatement ps = null;
            String sql = SQL_ADD_CUSTOMER;

            try {
                motorSql.connect();

                ps = motorSql.getConnection().prepareStatement(sql);


                ps.setString(1, customer.getName());
                ps.setString(2, customer.getSurname());
                ps.setString(3, customer.getEmail());
                ps.setString(4, customer.getPhone_number());
                ps.setString(5, customer.getUser_name());
                ps.setString(6, customer.getPassword());
                ps.setString(7, customer.getAddress());

                filas = motorSql.executeUpdate(ps);

            } catch (SQLException sqlException) {
                System.out.println(sqlException.getMessage());
            } finally {
                motorSql.disconnect();
            }

        } else {
            System.out.println("Error: El objeto recibido no es un producto válido o es nulo.");
        }
        return filas;
    }

    public Customer findById(int customerId) {
        Customer customer =null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = SQL_FIND_BY_ID;

        try {
            motorSql.connect();
            ps = motorSql.getConnection().prepareStatement(sql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();

            if (rs.next()) {
                customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("address"));

            }
        } catch (SQLException sqlException) {
            System.out.println("Error in findbyId" + sqlException.getMessage());
        } finally {
            motorSql.disconnect();
        }
        return customer;
    }


    //Metodos implementados de IDao

    @Override
    public int delete(Object e) {
        return 0;
    }

    @Override
    public int update(Object bean) {
        return 0;
    }

    public ArrayList findAll(Object bean) {

        ArrayList<Customer> lisCustomers = new ArrayList<Customer>();
        ResultSet rs = null;
        PreparedStatement ps = null;
        String sql = SQL_FIND_ALL;

        try {

            motorSql.connect();

            ps = motorSql.getConnection().prepareStatement(sql);

            rs = ps.executeQuery();


            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getString("address")

                );
                lisCustomers.add(customer);
            }

        } catch (SQLException sqlException) {
            System.out.println("ERROR en findAll:" + sqlException.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return lisCustomers;
    }
}
