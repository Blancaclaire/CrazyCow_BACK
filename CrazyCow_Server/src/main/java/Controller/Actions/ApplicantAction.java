package Controller.Actions;

import Model.DAO.ApplicantDao;
import Model.Entities.Applicant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

public class ApplicantAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action, Map<String, String[]> objectParams) {
        System.out.println("Valor recibido en action: " + action);
        String strReturn = "";

        switch (action) {

            case "ADD":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=APPLICANT.ADD&name=Emma&surname&Lopez&email=emma.lopez@example.com&phone_number=987654321&address=Avenida%20Libertad%2045&resume=emma_lopez_cv.pdf
                strReturn=add(objectParams);
                break;

            case "FIND_ALL":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=APPLICANT.FIND_ALL
                strReturn =findAll();
                break;
            default:
                strReturn ="ERROR.Invalid Action";
                break;
        }

        return strReturn;
    }

    public String add(Map<String, String[]>objectParams){

        String strReturn = "";
        ApplicantDao applicantDao = new ApplicantDao();
        Applicant applicant = new Applicant();

        try{

            //procesar name
            if (objectParams.get("name") != null && objectParams.get("name").length>0)
            {
                System.out.println("Valor recibido name: " + objectParams.get("name")[0]);
                applicant.setName(objectParams.get("name")[0]);
            }
            else{
                System.out.println("ERROR. El parámetro name es obligatorio.");
            }

            //procesar surname
            if (objectParams.get("surname") != null && objectParams.get("surname").length>0)
            {
                System.out.println("Valor recibido surname: " + objectParams.get("surname")[0]);
                applicant.setSurname(objectParams.get("surname")[0]);
            }
            else{
                System.out.println("ERROR. El parámetro surname es obligatorio.");
            }

            //procesar email
            if (objectParams.get("email") != null && objectParams.get("email").length>0)
            {
                System.out.println("Valor recibido email: " + objectParams.get("email")[0]);
                applicant.setEmail(objectParams.get("email")[0]);
            }
            else{
                System.out.println("ERROR. El parámetro email es obligatorio.");
            }

            //procesar phone_number
            if (objectParams.get("phone_number") != null && objectParams.get("phone_number").length>0)
            {
                System.out.println("Valor recibido phone_number: " + objectParams.get("phone_number")[0]);
                applicant.setPhone_number(objectParams.get("phone_number")[0]);
            }
            else{
                System.out.println("ERROR. El parámetro phone_number es obligatorio.");
            }

            //procesar address
            if (objectParams.get("address") != null && objectParams.get("address").length>0)
            {
                System.out.println("Valor recibido address: " + objectParams.get("address")[0]);
                applicant.setAddress(objectParams.get("address")[0]);
            }
            else{
                System.out.println("ERROR. El parámetro address es obligatorio.");
            }

            //procesar resume
            if (objectParams.get("resume") != null && objectParams.get("resume").length>0)
            {
                System.out.println("Valor recibido resume: " + objectParams.get("resume")[0]);
                applicant.setResume(objectParams.get("resume")[0]);
            }
            else{
                System.out.println("ERROR. El parámetro resume es obligatorio.");
            }



        }catch (Exception e){
            System.out.println("Error al procesar la solicitud de añadir un applicant.");
            strReturn = "Error al procesar la solicitud de añadir un applicant.";
        }

        int filasAfectadas = applicantDao.add(applicant);

        if (filasAfectadas > 0){
            System.out.println("Applicant añadido correctamente " + "las filas afectadas son: " + filasAfectadas);
            strReturn="Se ha podido añadir el applicant exitosamente";
        }
        else {
            strReturn = "No se pudo añadir el applicant";
        }

        return strReturn;
    }


    public String findAll(){
        String strReturn = null;
        ApplicantDao applicantDao = new ApplicantDao();
        Applicant applicant = new Applicant();
        ArrayList<Applicant> listApplicants = applicantDao.findAll(applicant);
        strReturn = Applicant.toArrayJson(listApplicants);
        return  strReturn;
    }
}
