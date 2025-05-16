package Model.DAO;

import Model.Entities.Employee;
import Model.MotorMySql.IMotorSql;
import Model.MotorMySql.MotorSql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDao implements IDao {

    private String SQL_FIND_BY_EMAIL = "SELECT * FROM EMPLOYEES WHERE email=?";
    private String SQL_FIND_ALL = "SELECT * FROM EMPLOYEES WHERE 1=1";
    private String SQL_LOGIN = "SELECT * FROM EMPLOYEES WHERE job_id=6003  and email = ? and password = ? "; //Solo se loguean los empleados cuyo job_id= IT/6003
    private IMotorSql motorSql;


    public EmployeeDao() {
        motorSql = new MotorSql();
    }


    public boolean authenticate(Object bean) {
        boolean isEmployee = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (bean != null && bean instanceof Employee) {
                Employee employee = (Employee) bean;
                motorSql.connect();
                ps = motorSql.getConnection().prepareStatement(SQL_LOGIN);
                ps.setString(1, employee.getEmail());
                ps.setString(2, employee.getPassword());

                rs = ps.executeQuery();
                isEmployee = rs.next();//Si hay resultados las credenciales son válidas
            }
        } catch (SQLException sqlEx) {
            System.out.println("ERROR al intentar comprobar la autenticacion del employee en EmployeeDao" + sqlEx.getMessage());
        } finally {
            motorSql.disconnect();
        }
        return isEmployee;
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
        ArrayList listEmployees = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = SQL_FIND_ALL;

        try {

            if (bean != null && bean instanceof Employee) {
                Employee employee = (Employee) bean;

                motorSql.connect();

                StringBuilder sqlBuilder = new StringBuilder(sql);
                ArrayList<Object> parameters = new ArrayList<>();

                if (employee.getEmail() != null) {
                    sqlBuilder.append(" AND email = ?");
                    parameters.add(employee.getEmail());
                }
                if (employee.getJob_id() > 0) {
                    sqlBuilder.append(" AND job_id = ?");
                    parameters.add(employee.getJob_id());
                }

                ps = motorSql.getConnection().prepareStatement(sqlBuilder.toString());

                for (int i = 0; i < parameters.size(); i++) {
                    if (parameters.get(i) instanceof String) {
                        ps.setString(i + 1, (String) parameters.get(i));
                    } else if (parameters.get(i) instanceof Integer) {
                        ps.setInt(i + 1, (Integer) parameters.get(i));
                    }
                }

                rs = ps.executeQuery();

                while (rs.next()) {
                    Employee employeeBd = new Employee(
                            rs.getInt("employee_id"),
                            rs.getInt("manager_id"),
                            rs.getInt("job_id"),
                            rs.getInt("restaurant_id"),
                            rs.getDouble("salary"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("email"),
                            rs.getString("phone_number"),
                            rs.getString("user_name"),
                            rs.getString("password"),
                            rs.getString("address"),
                            rs.getDate("start_date")

                    );


                    listEmployees.add(employeeBd);
                }
            } else {
                System.out.println("El objeto que se ha intentado añadir no es valido o es nulo");
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll Employees: " + e.getMessage());
        } finally {
            motorSql.disconnect();
        }
        return listEmployees;
    }
}
