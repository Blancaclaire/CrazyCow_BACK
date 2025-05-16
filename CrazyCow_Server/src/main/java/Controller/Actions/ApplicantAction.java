package Controller.Actions;

import Model.DAO.ApplicantDao;
import Model.DAO.ApplicationsApplicantDao;
import Model.Entities.Applicant;
import Model.Entities.ApplicationsApplicant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

/**
 * Clase que implementa IAction para manejar operaciones relacionadas con solicitantes (Applicants).
 * Gestiona las acciones de añadir nuevos solicitantes y listar todos los existentes.
 */
public class ApplicantAction implements IAction {

    /**
     * Metodo principal que ejecuta acciones basadas en el parámetro 'action'.
     *
     * @param request Objeto HttpServletRequest con los datos de la petición
     * @param response Objeto HttpServletResponse para la respuesta
     * @param action Acción a ejecutar (ADD, FIND_ALL)
     * @param objectParams Mapa de parámetros adicionales
     * @return String con resultado de la operación (mensaje de éxito/error o JSON)
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action, Map<String, String[]> objectParams) {
        System.out.println("Valor recibido en action: " + action);
        String strReturn = "";

        switch (action) {

            case "ADD":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=APPLICANT.ADD&name=Emma&surname&Lopez&email=emma.lopez@example.com&phone_number=987654321&address=Avenida%20Libertad%2045&resume=emma_lopez_cv.pdf
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=APPLICANT.ADD&name=Juan&surname=Martinez&email=juan.martinez@example.com&phone_number=612345678&address=Plaza%20Espa%C3%B1a%201&resume=juan_martinez_cv.pdf&job_offer_id=1
                strReturn = add(objectParams);
                break;

            case "FIND_ALL":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=APPLICANT.FIND_ALL
                strReturn = findAll();
                break;
            default:
                strReturn = "ERROR.Invalid Action";
                break;
        }

        return strReturn;
    }

    /**
     * Metodo para añadir un nuevo solicitante y su aplicación a una oferta de trabajo.
     *
     * @param objectParams Mapa con los parámetros del formulario:
     *        - name (obligatorio)
     *        - surname (obligatorio)
     *        - email (obligatorio)
     *        - phone_number (obligatorio)
     *        - address (obligatorio)
     *        - resume (obligatorio)
     *        - job_offer_id (obligatorio)
     * @return String con mensaje de éxito/error
     */
    public String add(Map<String, String[]> objectParams) {

        String strReturn = "";
        ApplicantDao applicantDao = new ApplicantDao();
        ApplicationsApplicantDao applicationsApplicantDao = new ApplicationsApplicantDao();
        Applicant applicant = new Applicant();

        try {

            //procesar name
            if (objectParams.get("name") != null && objectParams.get("name").length > 0) {
                System.out.println("Valor recibido name: " + objectParams.get("name")[0]);
                applicant.setName(objectParams.get("name")[0]);
            } else {
                System.out.println("ERROR. El parámetro name es obligatorio.");
            }

            //procesar surname
            if (objectParams.get("surname") != null && objectParams.get("surname").length > 0) {
                System.out.println("Valor recibido surname: " + objectParams.get("surname")[0]);
                applicant.setSurname(objectParams.get("surname")[0]);
            } else {
                System.out.println("ERROR. El parámetro surname es obligatorio.");
            }

            //procesar email
            if (objectParams.get("email") != null && objectParams.get("email").length > 0) {
                System.out.println("Valor recibido email: " + objectParams.get("email")[0]);
                applicant.setEmail(objectParams.get("email")[0]);
            } else {
                System.out.println("ERROR. El parámetro email es obligatorio.");
            }

            //procesar phone_number
            if (objectParams.get("phone_number") != null && objectParams.get("phone_number").length > 0) {
                System.out.println("Valor recibido phone_number: " + objectParams.get("phone_number")[0]);
                applicant.setPhone_number(objectParams.get("phone_number")[0]);
            } else {
                System.out.println("ERROR. El parámetro phone_number es obligatorio.");
            }

            //procesar address
            if (objectParams.get("address") != null && objectParams.get("address").length > 0) {
                System.out.println("Valor recibido address: " + objectParams.get("address")[0]);
                applicant.setAddress(objectParams.get("address")[0]);
            } else {
                System.out.println("ERROR. El parámetro address es obligatorio.");
            }

            //procesar resume
            if (objectParams.get("resume") != null && objectParams.get("resume").length > 0) {
                System.out.println("Valor recibido resume: " + objectParams.get("resume")[0]);
                applicant.setResume(objectParams.get("resume")[0]);
            } else {
                System.out.println("ERROR. El parámetro resume es obligatorio.");
            }


            int jobOfferId = 0;
            if (objectParams.get("job_offer_id") != null && objectParams.get("job_offer_id").length > 0) {
                try {
                    jobOfferId = Integer.parseInt(objectParams.get("job_offer_id")[0]);
                } catch (NumberFormatException e) {
                    return "ERROR. El job_offer_id debe ser un número válido.";
                }
            } else {
                return "ERROR. El parámetro job_offer_id es obligatorio.";
            }

            int filasAfectadas = applicantDao.add(applicant);

            if (filasAfectadas > 0) {
                System.out.println("Applicant añadido correctamente. Filas afectadas: " + filasAfectadas);

                // 2. Crear la relación con la oferta de trabajo
                ApplicationsApplicant application = new ApplicationsApplicant();
                application.setJob_offer_id(jobOfferId);
                application.setApplicant_id(applicant.getApplicant_id()); // Asume que el DAO actualiza el ID

                int appResult = applicationsApplicantDao.add(application);

                if (appResult > 0) {
                    strReturn = "Applicant y aplicación añadidos exitosamente";
                } else {
                    strReturn = "Applicant añadido pero falló la aplicación a la oferta";
                }
            } else {
                strReturn = " No se pudo añadir el applicant";
            }

        } catch (Exception e) {
            System.out.println("Error al procesar la solicitud de añadir un applicant.");
            strReturn = "Error al procesar la solicitud de añadir un applicant.";
        }


        return strReturn;
    }

    /**
     * Metodo para obtener todos los solicitantes en formato JSON.
     *
     * @return String con representación JSON de la lista de solicitantes
     */

    public String findAll() {
        String strReturn = null;
        ApplicantDao applicantDao = new ApplicantDao();
        Applicant applicant = new Applicant();
        ArrayList<Applicant> listApplicants = applicantDao.findAll(applicant);
        strReturn = Applicant.toArrayJson(listApplicants);
        return strReturn;
    }
}
