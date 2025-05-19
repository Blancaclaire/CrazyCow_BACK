package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Interfaz que define el contrato para las acciones procesadas en el controlador.
 * Las implementaciones de esta interfaz representan operaciones específicas
 * que pueden ser ejecutadas en respuesta a solicitudes HTTP.
 */
public interface IAction {

    /**
     * Metodo principal que ejecuta la lógica de la acción.
     *
     * @param request Objeto HttpServletRequest que contiene la solicitud del cliente
     * @param response Objeto HttpServletResponse para enviar la respuesta al cliente
     * @param action Nombre de la acción a ejecutar (para routing dinámico)
     * @param objectParams Mapa de parámetros adicionales (puede incluir formularios multipart, JSON, etc.)
     * @return String que representa el resultado de la acción (generalmente una ruta de redirección o vista)

     */
    String execute (HttpServletRequest request, HttpServletResponse response, String action, Map<String,String[]> objectParams);

}
