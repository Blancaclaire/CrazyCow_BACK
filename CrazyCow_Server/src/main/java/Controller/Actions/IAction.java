package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IAction {

    //Define metodo "ejecutar" que será implementado por las clases que manejan diferentes acciones
    //La función recibe una solicitud HTTP, una respuesta HTTP, y una cadena de accion como parámetros
    //Devuelve una cadena
    String execute (HttpServletRequest request, HttpServletResponse response, String action);
}
