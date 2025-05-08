package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BurguerAction implements IAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {

        String strReturn = "";

        switch (action) {
            case "FIND_FIRST":
                //Implementacion Pendiente
                break;
            case "FIND_ALL":
                strReturn=finAll();
                break;
            case "ADD_INGREDIENT":
                
                break;
            case "DELETE_INGREDIENT":
                break;

        }

        return strReturn;
    }

    private String finAll(){
        return "busca todas";
    }
}
