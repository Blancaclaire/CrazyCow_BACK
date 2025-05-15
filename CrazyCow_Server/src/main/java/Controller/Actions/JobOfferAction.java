package Controller.Actions;

import Model.DAO.JobOfferDao;
import Model.DAO.ProductDao;
import Model.Entities.JobOffer;
import Model.Entities.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

public class JobOfferAction implements IAction{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action, Map<String, String[]> objectParams) {

        System.out.println("Valor recibido en action: " + action);
        String strReturn = "";

        switch (action) {

            case "FIND_ALL":
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=JOB_OFFER.FIND_ALL
                //http://localhost:8080/CrazyCow_Server/Controller?ACTION=JOB_OFFER.FIND_ALL&job_offer_id=1
                strReturn = findAll(objectParams);
                break;

            default:
                strReturn ="ERROR.Invalid Action";
                break;
        }

        return strReturn;
    }

    public  String findAll(Map<String, String[]>objectParams){
        String strReturn = "";
        JobOfferDao jobOfferDao = new JobOfferDao();
        JobOffer filter = new JobOffer();

        if (objectParams.get("job_offer_id") != null && objectParams.get("job_offer_id").length > 0) {
            filter.setJob_offer_id(Integer.parseInt(objectParams.get("job_offer_id")[0]));
        }

        ArrayList<JobOffer> listOffers = jobOfferDao.findAll(filter);
        return  JobOffer.toArrayJson(listOffers);

    }



}
