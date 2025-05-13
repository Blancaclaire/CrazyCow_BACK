package Controller;

import Controller.Actions.CustomerAction;
import Controller.Actions.ProductAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

//Definicion del servlet
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})


//la clase maneja solicitudes HTTP
public class Controller extends HttpServlet implements IController {
    //Metodo para procesar la peticion HTTP(request)
    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain;charset=UTF-8"); //Establece el tipo de contenido
        //Se configura los encabezados CORS para permitir que otros clientes (JavaScript) hagan solicitudes al servidor

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");

        //PrintWriter es una clase de java que se usa para escribir texto  en salida de datos
        //out se convierte en un objeto PrintWriter que usaremos para escribir datos en la respuesta;

        PrintWriter out = response.getWriter();
        String strAction = request.getParameter("ACTION");//Captura un parámetro de la URL llamado ACTION
        String[] arrayAction = new String[2]; //Guarda los parametros de la accion
        Map<String, String[]> objectParams = request.getParameterMap();
        if (strAction != "") {
            arrayAction = strAction.split("\\."); //Divide el parametro en dos partes por un (.)
        }


        switch (arrayAction[0].toUpperCase()) {
            case "PRODUCT": {
                out.print(new ProductAction().execute(request, response, arrayAction[1], objectParams));
                break;
            }
            case "CUSTOMER":   {
                out.print(new CustomerAction().execute(request,response,arrayAction[1],objectParams));
                break;
            }

            default: {
                System.out.println(arrayAction[0]);
                throw new ServletException("Action " + arrayAction[0] + "not valid");
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        }
        catch (ServletException e){
            System.out.println("ERROR. The server was unable to perform the doGet request. ");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        }
        catch (ServletException e){
            System.out.println("ERROR. The server was unable to perform the doPost request. ");
        }

    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        }
        catch (ServletException e){
            System.out.println("ERROR. The server was unable to perform the doPut request. ");
        }

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        }
        catch (ServletException e){
            System.out.println("ERROR. The server was unable to perform the doDelete request. ");
        }

    }




}
