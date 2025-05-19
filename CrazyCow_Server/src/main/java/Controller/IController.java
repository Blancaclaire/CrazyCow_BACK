package Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interfaz que define el contrato para los controladores de la aplicación.
 * Establece los métodos obligatorios que deben implementar todos los controladores
 * para manejar solicitudes HTTP básicas (GET, POST, PUT, DELETE) y un método
 * unificado para procesamiento de solicitudes.
 *
 * <p>Esta interfaz sigue el patrón de diseño Front Controller y asegura
 * consistencia en el manejo de peticiones HTTP across the application.</p>
 */

public interface IController {
    /**
     * Metodo centralizado para procesamiento de todas las solicitudes HTTP.
     * Debe contener la lógica principal de enrutamiento y procesamiento.
     *
     * @param request El objeto HttpServletRequest con los datos de la solicitud
     * @param response El objeto HttpServletResponse para construir la respuesta
     * @throws ServletException Si ocurre un error específico del servlet
     * @throws IOException Si ocurre un error de entrada/salida durante el procesamiento
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Maneja solicitudes HTTP GET. Usado para operaciones de consulta/lectura
     * que no modifican recursos (idempotentes).
     *
     * @param request El objeto HttpServletRequest con los datos de la solicitud
     * @param response El objeto HttpServletResponse para construir la respuesta
     * @throws ServletException Si ocurre un error específico del servlet
     * @throws IOException Si ocurre un error de entrada/salida durante el procesamiento
     */

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Maneja solicitudes HTTP POST. Usado para operaciones que crean nuevos recursos
     * o realizan acciones con efectos secundarios.
     *
     * @param request El objeto HttpServletRequest con los datos de la solicitud
     * @param response El objeto HttpServletResponse para construir la respuesta
     * @throws ServletException Si ocurre un error específico del servlet
     * @throws IOException Si ocurre un error de entrada/salida durante el procesamiento
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Maneja solicitudes HTTP PUT. Usado para operaciones de actualización completa
     * de recursos existentes (idempotentes).
     *
     * @param request El objeto HttpServletRequest con los datos de la solicitud
     * @param response El objeto HttpServletResponse para construir la respuesta
     * @throws ServletException Si ocurre un error específico del servlet
     * @throws IOException Si ocurre un error de entrada/salida durante el procesamiento
     */
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    /**
     * Maneja solicitudes HTTP DELETE. Usado para operaciones de eliminación de recursos
     * (idempotentes).
     *
     * @param request El objeto HttpServletRequest con los datos de la solicitud
     * @param response El objeto HttpServletResponse para construir la respuesta
     * @throws ServletException Si ocurre un error específico del servlet
     * @throws IOException Si ocurre un error de entrada/salida durante el procesamiento
     */
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
