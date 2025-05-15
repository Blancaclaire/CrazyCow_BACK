package Controller.Actions;

import Model.DAO.EmployeeDao;
import Model.DAO.OrderDao;
import Model.Entities.Employee;
import Model.Entities.Order;
import Model.Entities.OrderDetail;
import Model.Entities.Payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

public class OrderAction implements IAction {
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
                strReturn = add(objectParams);
                break;
            default:
                strReturn = "ERROR.Invalid Action";
                break;
        }

        return strReturn;
    }

    public String findAll() {
        String strReturn = "";
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
            // 1. Validar parámetros obligatorios
            if (!objectParams.containsKey("customer_id") || !objectParams.containsKey("restaurant_id")
                    || !objectParams.containsKey("total") || !objectParams.containsKey("location")) {
                return "ERROR: Faltan parámetros obligatorios (customer_id, restaurant_id, total, location)";
            }

            // 2. Setear datos básicos del Order
            newOrder.setCustomer_id(Integer.parseInt(objectParams.get("customer_id")[0]));
            newOrder.setRestaurant_id(Integer.parseInt(objectParams.get("restaurant_id")[0]));
            newOrder.setTotal(Double.parseDouble(objectParams.get("total")[0]));
            newOrder.setLocation(objectParams.get("location")[0]);

            // Si no se proporciona order_status, se establece "pendiente" por defecto
            newOrder.setOrder_status(objectParams.containsKey("order_status") ?
                    objectParams.get("order_status")[0] : "pendiente");

            // 3. Procesar productos (OrderDetails)
            if (objectParams.containsKey("order_details")) {
                String[] products = objectParams.get("order_details")[0].split(",");

                for (String product : products) {
                    String[] parts = product.split(":");
                    if (parts.length >= 2) {
                        OrderDetail detail = new OrderDetail();
                        detail.setProduct_id(Integer.parseInt(parts[0]));
                        detail.setQuantity(Integer.parseInt(parts[1]));
                        newOrder.addOrderDetail(detail);
                    }
                }
                if (newOrder.getOrder_details().isEmpty()) {
                    return "ERROR: Formato de order_details inválido. Usar format product_id:quantity,product_id:quantity";
                }
            } else {
                return "ERROR: Se requieren detalles del pedido (parámetro 'order_details')";
            }

            // 4. Procesar Payment
            if (objectParams.containsKey("holder_name") && objectParams.containsKey("holder_number") &&
                    objectParams.containsKey("cvv") && objectParams.containsKey("card_type")) {

                Payment payment = new Payment(
                        objectParams.get("holder_name")[0],
                        objectParams.get("holder_number")[0],
                        objectParams.get("cvv")[0],
                        objectParams.get("card_type")[0],
                        newOrder.getTotal()
                );
                newOrder.setPayment(payment);
            } else {
                return "ERROR: Faltan datos de pago (holder_name, holder_number, cvv, card_type)";
            }

            // 5. Guardar (OrderDao manejará la transacción)
            int filasAfectadas = orderDao.add(newOrder);
            if (filasAfectadas > 0) {
                strReturn = "Pedido añadido correctamente";
            } else {
                strReturn = "ERROR: No se pudo insertar el pedido";
            }

        } catch (NumberFormatException e) {
            strReturn = "ERROR: Parámetros numéricos inválidos: " + e.getMessage();
        } catch (Exception e) {
            strReturn = "ERROR interno: " + e.getMessage();
            e.printStackTrace();
        }
        return strReturn;
    }
}

//http://localhost:8080/CrazyCow_Server/Controller?ACTION=ORDER.ADD&customer_id=13001&restaurant_id=10000&order_status=Preparation&total=29.99&location=Calle%20Principal%20123&order_details=8000:2,80
// 01:1&holder_name=John%20Doe&holder_number=1234567890123456&cvv=123&card_type=VISA


