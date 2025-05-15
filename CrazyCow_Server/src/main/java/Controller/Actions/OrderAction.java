package Controller.Actions;

import Model.DAO.EmployeeDao;
import Model.DAO.OrderDao;
import Model.Entities.Employee;
import Model.Entities.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

public class OrderAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action, Map<String, String[]> objectParams) {

        System.out.println("Valor recibido en action: " + action);
        String strReturn = "";

        switch (action) {

            case "FIND_ALL":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=ORDER.FIND_ALL
                strReturn = findAll();
                break;
            case "ADD":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=ORDER.ADD&customer_id=123&restaurant_id=456&order_status=pendiente&total=29.99&location=Calle%20Principal%20123
                strReturn=add(objectParams);
                break;
            default:
                strReturn ="ERROR.Invalid Action";
                break;
        }

        return strReturn;
    }

    public String findAll(){
        String strReturn ="";
        OrderDao orderDao = new OrderDao();
        Order order = new Order();
        ArrayList<Order> listOrders = orderDao.findAll(order);

        return Order.toArrayJson(listOrders);

    }

    private String add(Map<String, String[]> objectParams) {
        OrderDao orderDao = new OrderDao();
        Order newOrder = new Order();
        String strReturn = "";

        try {
            // Procesar customer_id
            if (objectParams.get("customer_id") != null && objectParams.get("customer_id").length > 0) {
                System.out.println("Valor recibido customer_id: " + objectParams.get("customer_id")[0]);
                newOrder.setCustomer_id(Integer.parseInt(objectParams.get("customer_id")[0]));
            } else {
                System.out.println("Error: El parámetro customer_id es obligatorio.");
                return "Error: El parámetro customer_id es obligatorio.";
            }

            // Procesar restaurant_id
            if (objectParams.get("restaurant_id") != null && objectParams.get("restaurant_id").length > 0) {
                System.out.println("Valor recibido restaurant_id: " + objectParams.get("restaurant_id")[0]);
                newOrder.setRestaurant_id(Integer.parseInt(objectParams.get("restaurant_id")[0]));
            } else {
                System.out.println("Error: El parámetro restaurant_id es obligatorio.");
                return "Error: El parámetro restaurant_id es obligatorio.";
            }

            // Procesar order_status (puede tener un valor por defecto si no se proporciona)
            if (objectParams.get("order_status") != null && objectParams.get("order_status").length > 0) {
                System.out.println("Valor recibido order_status: " + objectParams.get("order_status")[0]);
                newOrder.setOrder_status(objectParams.get("order_status")[0]);
            } else {
                // Valor por defecto para el estado del pedido
                newOrder.setOrder_status("pendiente");
                System.out.println("Valor por defecto para order_status: pendiente");
            }

            // Procesar total
            if (objectParams.get("total") != null && objectParams.get("total").length > 0) {
                System.out.println("Valor recibido total: " + objectParams.get("total")[0]);
                try {
                    double total = Double.parseDouble(objectParams.get("total")[0]);
                    newOrder.setTotal(total);
                } catch (NumberFormatException e) {
                    System.out.println("Error: El parámetro total debe ser un número válido.");
                    return "Error: El parámetro total debe ser un número válido.";
                }
            } else {
                System.out.println("Error: El parámetro total es obligatorio.");
            }

            // Procesar location
            if (objectParams.get("location") != null && objectParams.get("location").length > 0) {
                System.out.println("Valor recibido location: " + objectParams.get("location")[0]);
                newOrder.setLocation(objectParams.get("location")[0]);
            } else {
                System.out.println("Error: El parámetro location es obligatorio.");
                return "Error: El parámetro location es obligatorio.";
            }

            int filasAfectadas = orderDao.add(newOrder);

            if (filasAfectadas > 0) {
                strReturn = "Pedido añadido correctamente. Filas afectadas: " + filasAfectadas;

                // Aquí podrías añadir la lógica para insertar los detalles del pedido
                // como mencionas en el comentario de OrderDao

            } else {
                strReturn = "No se pudo añadir el pedido";
            }

        } catch (Exception e) {
            System.out.println("Error al procesar la solicitud de añadir pedido: " + e.getMessage());
            strReturn = "Error interno al procesar la solicitud";
        }

        return strReturn;
    }

}
