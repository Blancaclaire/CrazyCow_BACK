package Model.DAO;

import Model.Entities.Employee;
import Model.Entities.Restaurant;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RestaurantDao {


    private final String SQL_FIND_BY_EMPLOYEE = " SELECT RE.restaurant_id FROM RESTAURANTS RE inner join EMPLOYEES EMP ON RE.restaurant_id=EMP.restaurant_id where EMP.employee_id=? ";
    private IMotorSql motorSql;


    public RestaurantDao() {
        motorSql = new MotorSql();
    }

    public Restaurant findByIdEmployee(Object bean) {

        Restaurant restaurant = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = SQL_FIND_BY_EMPLOYEE;

        try {

            if (bean != null && bean instanceof Employee) {
                motorSql.connect();
                Employee employee = (Employee) bean;
                ps = motorSql.getConnection().prepareStatement(sql);
                ps.setInt(1, employee.getEmployee_id());

                rs = ps.executeQuery();


                if (rs.next()) {
                    restaurant = new Restaurant(
                            rs.getInt("restaurant_id")
                    );

                }
            } else {
                System.out.println("El objeto introducido no es un empelado");
            }
        } catch (SQLException e) {
            System.out.println("Error en findByEmployeeIdRestaurants" + e.getMessage());
        } finally {
            motorSql.disconnect();
        }

        return restaurant;
    }
}
