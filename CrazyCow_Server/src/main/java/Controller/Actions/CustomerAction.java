package Controller.Actions;

import Model.DAO.CustomerDao;
import Model.Entities.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Map;

public class CustomerAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action, Map<String, String[]> objectParams) {
        System.out.println("VAlor recibido en action: " + action);
        String strReturn = "";

        switch (action) {
            case "LOGIN":
                strReturn = "";
                break;

            case "ADD":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=CUSTOMER.ADD&name=John&surname=Doe&email=john.doe@example.com&phone_number=123456789&user_name=johndoe&password=securepassword&address=123%20Main%20Street
                strReturn =add(objectParams);
                break;
            default:
                strReturn ="ERROR.Invalid Action";
                break;
        }

        return strReturn;
    }

    public String add (Map<String, String[]>objectParams){
        String strReturn = "";
        CustomerDao customerDao = new CustomerDao();
        Customer customer = new Customer();

        try
        {

            // Procesar name
            if (objectParams.get("name")!=null && objectParams.get("name").length>0)
            {
                System.out.println("VAlor recibido name: " + objectParams.get("name")[0]);
                customer.setName(objectParams.get("name")[0]);
            }
            else{
                System.out.println("ERROR. El parámetro name es obligatorio");
            }


            // Procesar surname
            if (objectParams.get("surname")!=null && objectParams.get("surname").length>0)
            {
                System.out.println("VAlor recibido surname: " + objectParams.get("surname")[0]);
                customer.setSurname(objectParams.get("surname")[0]);
            }
            else{
                System.out.println("ERROR. El parámetro surname es obligatorio");
            }


            // Procesar email
            if (objectParams.get("email")!=null && objectParams.get("email").length>0)
            {
                System.out.println("VAlor recibido email: " + objectParams.get("email")[0]);
                customer.setEmail(objectParams.get("email")[0]);
            }
            else{
                System.out.println("ERROR. El parámetro email es obligatorio");
            }


            // Procesar phone_number
            if (objectParams.get("phone_number")!=null && objectParams.get("phone_number").length>0)
            {
                System.out.println("VAlor recibido phone_number: " + objectParams.get("phone_number")[0]);
                customer.setPhone_number(objectParams.get("phone_number")[0]);
            }
            else{
                System.out.println("ERROR. El parámetro phone_number es obligatorio");
            }


            // Procesar user_name
            if (objectParams.get("user_name")!=null && objectParams.get("user_name").length>0)
            {
                System.out.println("VAlor recibido user_name: " + objectParams.get("user_name")[0]);
                customer.setUser_name(objectParams.get("user_name")[0]);
            }
            else{
                System.out.println("ERROR. El parámetro user_name es obligatorio");
            }

            // Procesar password
            if (objectParams.get("password")!=null && objectParams.get("password").length>0)
            {
                System.out.println("VAlor recibido password: " + objectParams.get("password")[0]);
                customer.setPassword(objectParams.get("password")[0]);
            }
            else{
                System.out.println("ERROR. El parámetro password es obligatorio");
            }


            // Procesar address
            if (objectParams.get("address")!=null && objectParams.get("address").length>0)
            {
                System.out.println("VAlor recibido address: " + objectParams.get("address")[0]);
                customer.setAddress(objectParams.get("address")[0]);
            }
            else{
                System.out.println("ERROR. El parámetro address es obligatorio");
            }

            int filasAfectadas = customerDao.add(customer);

            if(filasAfectadas >0){
                System.out.println("Custommer añadido correctamente" +  "las filas afectadas son: " + filasAfectadas);
            }
            else {
                strReturn="No se pudo añadir el customer";
            }

        }
        catch (Exception e){
            System.out.println("Error al procesar a solicitud de añadir un customer");
            strReturn= "Error al procesar a solicitud de añadir un customer";
        }
        return strReturn;
    }
}
