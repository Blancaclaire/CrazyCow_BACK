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

}
