package Controller;

import Controller.Actions.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

/**
 * Servlet principal que actúa como controlador de la aplicación.
 * Maneja todas las solicitudes HTTP y las redirige a las acciones correspondientes.
 * Implementa la interfaz IController para garantizar la estructura requerida.
 */

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})


public class Controller extends HttpServlet implements IController {

    /**
     * Procesa todas las solicitudes HTTP entrantes.
     * Configura CORS, determina la acción solicitada y delega la ejecución al Action correspondiente.
     *
     * @param request Objeto HttpServletRequest con los datos de la solicitud
     * @param response Objeto HttpServletResponse para preparar la respuesta
     * @throws ServletException Si ocurre un error en el servlet
     * @throws IOException Si ocurre un error de entrada/salida
     */

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Configuración básica de la respuesta
        response.setContentType("text/plain;charset=UTF-8");


        // Configuración de cabeceras CORS para permitir peticiones desde cualquier origen (*)
        // y métodos HTTP especificados
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");


        PrintWriter out = response.getWriter();

        // Obtiene el parámetro ACTION que define qué operación realizar
        String strAction = request.getParameter("ACTION");
        String[] arrayAction = new String[2]; // [0] = Entidad, [1] = Operación
        Map<String, String[]> objectParams = request.getParameterMap();

        // Si existe acción, la separamos en entidad.operación (ej: "PRODUCT.FIND_ALL")
        if (strAction != "") {
            arrayAction = strAction.split("\\.");
        }

        // Router principal: delega la ejecución al Action correspondiente
        switch (arrayAction[0].toUpperCase()) {
            case "PRODUCT": {
                out.print(new ProductAction().execute(request, response, arrayAction[1], objectParams));
                break;
            }
            case "CUSTOMER":   {
                out.print(new CustomerAction().execute(request,response,arrayAction[1],objectParams));
                break;
            }
            case "EMPLOYEE":   {
                out.print(new EmployeeAction().execute(request,response,arrayAction[1],objectParams));
                break;
            }
            case "ORDER":   {
                out.print(new OrderAction().execute(request,response,arrayAction[1],objectParams));
                break;
            }
            case "JOB_OFFER":{
                out.print(new JobOfferAction().execute(request,response,arrayAction[1],objectParams));
                break;
            }
            case "APPLICANT":{
                out.print(new ApplicantAction().execute(request,response,arrayAction[1],objectParams));
                break;
            }

            default: {
                System.out.println(arrayAction[0]);
                throw new ServletException("Action " + arrayAction[0] + "not valid");
            }
        }


    }


    // ========== MÉTODOS HTTP ========== //

    /**
     * Maneja las solicitudes GET.
     * Delega el procesamiento al método processRequest centralizado.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        }
        catch (ServletException e){
            System.out.println("ERROR. The server was unable to perform the doGet request. ");
        }

    }

    /**
     * Maneja las solicitudes POST.
     * Delega el procesamiento al método processRequest centralizado.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        }
        catch (ServletException e){
            System.out.println("ERROR. The server was unable to perform the doPost request. ");
        }

    }

    /**
     * Maneja las solicitudes PUT.
     * Delega el procesamiento al método processRequest centralizado.
     */
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        }
        catch (ServletException e){
            System.out.println("ERROR. The server was unable to perform the doPut request. ");
        }

    }

    /**
     * Maneja las solicitudes DELETE.
     * Delega el procesamiento al método processRequest centralizado.
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        }
        catch (ServletException e){
            System.out.println("ERROR. The server was unable to perform the doDelete request. ");
        }

    }




}
