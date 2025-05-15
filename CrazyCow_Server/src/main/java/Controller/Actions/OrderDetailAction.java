package Controller.Actions;

import Model.DAO.OrderDetailDao;
import Model.Entities.OrderDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class OrderDetailAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action, Map<String, String[]> objectParams) {

        System.out.println("Valor recibido en action: " + action);
        String strReturn = "";

        switch (action) {

            case "ADD":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=ORDERDETAIL.ADD&order_id=11000&product_id=8000&quantity=2;
                strReturn=add(objectParams);
                break;
            default:
                strReturn ="ERROR.Invalid Action";
                break;
        }

        return strReturn;
    }

    public String add (Map<String, String[]> objectParams){
        OrderDetailDao orderDetailDao = new OrderDetailDao();
        OrderDetail orderDetail = new OrderDetail();
        String strReturn = "";

        try{

            // Extraer los parámetros del Map y asignarlos al OrderDetail
            if (objectParams.containsKey("order_id") && objectParams.get("order_id") != null) {
                orderDetail.setOrder_id(Integer.parseInt(objectParams.get("order_id")[0]));
            }
            if (objectParams.containsKey("product_id") && objectParams.get("product_id") != null) {
                orderDetail.setProduct_id(Integer.parseInt(objectParams.get("product_id")[0]));
            }
            if (objectParams.containsKey("quantity") && objectParams.get("quantity") != null) {
                orderDetail.setQuantity(Integer.parseInt(objectParams.get("quantity")[0]));
            }

            // Llamar al DAO para insertar el detalle del pedido
            int result = orderDetailDao.add(orderDetail);

            if (result > 0) {
                strReturn = "Detalle de pedido añadido correctamente";
            } else {
                strReturn = "No se pudo añadir el detalle del pedido";
            }

        } catch (Exception e) {
            System.out.println("Error al procesar la solicitud de añadir los detalles del pedido: " + e.getMessage());
            strReturn = "Error interno al procesar la solicitud";
        }
        return strReturn;
    }
}
