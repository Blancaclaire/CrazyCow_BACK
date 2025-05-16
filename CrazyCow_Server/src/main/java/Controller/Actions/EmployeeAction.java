package Controller.Actions;

import Model.DAO.CustomerDao;
import Model.DAO.EmployeeDao;
import Model.Entities.Customer;
import Model.Entities.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

/**
 * Controlador para gestionar operaciones relacionadas con empleados.
 * Implementa las acciones de autenticación y consulta de empleados.
 */
public class EmployeeAction implements  IAction{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action, Map<String, String[]> objectParams) {
        System.out.println("Valor recibido en action: " + action);
        String strReturn = "";

        switch (action) {

            case "FIND_ALL":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=EMPLOYEE.FIND_ALL&email=clara.alonso.jimenez@gmail.com
                strReturn =findAll(objectParams);
                break;

            case "LOGIN" :
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=EMPLOYEE.LOGIN&email=clara.alonso.jimenez@gmail.com&password=12345
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=EMPLOYEE.LOGIN&email=john.doe@example.com&password=securepassword
                strReturn = authenticate(objectParams);
                break;

            default:
                strReturn ="ERROR.Invalid Action";
                break;
        }

        return strReturn;
    }

    /**
     * Obtiene lista de empleados con filtros opcionales.
     * @param objectParams Puede contener:
     *        - email: filtro por correo electrónico
     *        - job_id: filtro por ID de puesto
     * @return JSON con lista de empleados
     */
    public String findAll(Map<String, String[]>objectParams){
        String strReturn = "";
        EmployeeDao employeeDao = new EmployeeDao();

        Employee employee = new Employee();

        //Procesamos email
        if (objectParams.get("email") != null && objectParams.get("email").length > 0) {
            System.out.println("Valor recibido email " + objectParams.get("email")[0]);
            employee.setEmail(objectParams.get("email")[0]);
        }
        //Procesamos job_id
        if (objectParams.get("job_id") != null && objectParams.get("job_id").length > 0) {
            System.out.println("Valor recibido job_id: " + objectParams.get("job_id")[0]);
            employee.setJob_id(Integer.parseInt(objectParams.get("job_id")[0]));
        }


        ArrayList<Employee> listEmployees = employeeDao.findAll(employee);
        strReturn = Employee.toArrayJson(listEmployees);
        return strReturn;
    }

    /**
     * Autentica un empleado.
     * @param objectParams Debe contener:
     *        - email: correo del empleado
     *        - password: contraseña
     * @return "OK" si éxito, "NO" si falla
     */
    public String authenticate(Map<String, String[]>objectParams){
        String strReturn="";

        try{
            EmployeeDao employeeDao = new EmployeeDao();
            Employee employee = new Employee();

            //Procesar email y password
            if (objectParams.get("email") != null && objectParams.get("email").length > 0) {
                employee.setEmail((objectParams.get("email")[0]));
            }
            if (objectParams.get("password") != null && objectParams.get("password").length > 0) {
                employee.setPassword(objectParams.get("password")[0]);
            }

            boolean isEmployee = employeeDao.authenticate(employee);
            if (isEmployee) {
                System.out.println("El empleado puede loguearse");
                strReturn = "OK";
            }else{
                System.out.println("ERROR. El empleado no puede hacer login o no tiene las credenciales necesarias");
                strReturn = "NO";
            }

        }catch (Exception e){
            strReturn = "LOGIN ERROR"+ e.getMessage();
        }
        return strReturn;
    }
}
