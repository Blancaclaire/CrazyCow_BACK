package Controller.Actions;

import Model.DAO.CustomerDao;
import Model.Entities.Customer;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Clase que implementa IAction para gestionar operaciones de clientes (Customer).
 * Maneja registro, autenticación y consultas de clientes.
 */
public class CustomerAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action, Map<String, String[]> objectParams) {
        System.out.println("Valor recibido en action: " + action);
        String strReturn = "";

        switch (action) {

            case "FIND_ALL":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=CUSTOMER.FIND_ALL
                strReturn =findAll();
                break;

            case "FIND_BY_EMAIL":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=CUSTOMER.FIND_BY_EMAIL&email=michael.johnson@example.com
                strReturn =findByEmail(objectParams);
                break;

            case "LOGIN":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=CUSTOMER.LOGIN&email=john.doe@example.com&password=securepassword
                strReturn = authenticate(objectParams);
                break;

            case "REGISTER":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=CUSTOMER.REGISTER&name=John&surname=Doe&email=clara@gmail.com&phone_number=123456789&user_name=blanca&password=securepassword&address=123%20Main%20Street
                strReturn =register(objectParams);
                break;
            default:
                strReturn ="ERROR.Invalid Action";
                break;
        }

        return strReturn;
    }

    /**
     * Obtiene todos los clientes en formato JSON.
     */
    public String findAll(){
        String strReturn ="";
        CustomerDao customerDao = new CustomerDao();

        Customer customer = new Customer();
        ArrayList<Customer> listCustomer = customerDao.findAll(customer);

        return Customer.toArrayJson(listCustomer);
    }

    /**
     * Busca un cliente por email.
     * @param objectParams Debe contener email
     */
    public  String findByEmail(Map<String, String[]>objectParams){
        String strReturn="";
        CustomerDao customerDao = new CustomerDao();
        Customer filtro = new Customer();
        if (objectParams.get("email")!=null && objectParams.get("email").length>0){
            filtro.setEmail(objectParams.get("email")[0]);
        }
        else {
            System.out.println("El parametro email no llego correctamente" );
        }
        Customer customer = customerDao.findByEmail(filtro.getEmail());
        strReturn=Customer.toJson(customer);

        return strReturn;
    }

    /**
     * Autentica un cliente con email y password.
     * @param objectParams Debe contener email y password
     */
    public String authenticate(Map<String, String[]>objectParams){
        String strReturn= "";

        try {
            CustomerDao customerDao = new CustomerDao();
            Customer customer = new Customer();

            //Procesar email y password
            if (objectParams.get("email") != null && objectParams.get("email").length > 0) {
                customer.setEmail((objectParams.get("email")[0]));
            }
            if (objectParams.get("password") != null && objectParams.get("password").length > 0) {
                customer.setPassword(objectParams.get("password")[0]);
            }


            boolean isCustomer = customerDao.authenticate(customer);
            if (isCustomer) {
                System.out.println("El usuario puede hacer login");
                strReturn = "OK";
            } else {
                System.out.println("ERROR. El usuario no puede hacer login");
                strReturn = "NO";
            }

        }catch (Exception e){
            strReturn="LOGIN ERROR" + e.getMessage();
        }
        return strReturn;

    }

    /**
     * Registra un nuevo cliente.
     * @param objectParams Debe contener todos los campos obligatorios
     */
    public String register (Map<String, String[]>objectParams){
        String strReturn = "";
        CustomerDao customerDao = new CustomerDao();
        Customer customer = new Customer();

        try
        {

            // Procesar name
            if (objectParams.get("name")!=null && objectParams.get("name").length>0)
            {
                System.out.println("Valor recibido name: " + objectParams.get("name")[0]);
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
